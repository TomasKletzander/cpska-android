package cz.dmn.cpska

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.Foggle
import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.api.cfg.Configuration
import cz.dmn.cpska.di.AppModule
import cz.dmn.cpska.di.DaggerAppComponent
import cz.dmn.cpska.util.FavoriteClubsManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import io.reactivex.Observable
import javax.inject.Inject

class CpsApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var clubsCache: MemoryCache<List<Club>>
    @Inject lateinit var competitionsCache: MemoryCache<List<Competition>>
    @Inject lateinit var favoriteClubsManager: FavoriteClubsManager
    lateinit var configuration: Configuration

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics(), Answers())

        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun initialize(): Observable<Boolean> =
        dataManager.getConfiguration()
                .doOnNext { configuration = it }
                .doOnError {
                    Crashlytics.log("Failed to get configuration")
                    Crashlytics.logException(it)
                }
                .flatMap {
                    dataManager.getFoggles()
                            .doOnNext { saveFoggles(it) }
                            .doOnError {
                                Crashlytics.log("Failed to get foggles")
                                Crashlytics.logException(it)
                            }
                }
                .flatMap {
                    dataManager.getClubs()
                            .doOnNext { clubsCache.data = it }
                            .doOnError {
                                Crashlytics.log("Failed to get clubs")
                                Crashlytics.logException(it)
                            }
                }
                .flatMap {
                    dataManager.getCompetitions()
                            .doOnNext { competitionsCache.data = it }
                            .doOnError {
                                Crashlytics.log("Failed to get competitions")
                                Crashlytics.logException(it)
                            }
                }.flatMap {
                    favoriteClubsManager.initialize()
                            .doOnError {
                                Crashlytics.log("Failed to initialize favorite clubs")
                                Crashlytics.logException(it)
                            }
                }

    private fun saveFoggles(apiData: Map<String, Boolean>) {
        Foggle.values().forEach {
            if (apiData.containsKey(it.key)) {
                it.isEnabled = apiData[it.key]!!
            } else {
                it.isEnabled = it.defaultEnabled
            }
        }
    }
}