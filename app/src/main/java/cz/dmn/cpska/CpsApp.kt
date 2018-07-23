package cz.dmn.cpska

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.AppModule
import cz.dmn.cpska.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import io.reactivex.Observable
import javax.inject.Inject

class CpsApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var dataManager: DataManager
    val clubs = mutableListOf<Club>()

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics(), Answers())

        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    fun initialize(): Observable<Boolean> = dataManager.getClubs().map {
        clubs.clear()
        clubs.addAll(it)
        true
    }
}