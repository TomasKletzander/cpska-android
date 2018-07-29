package cz.dmn.cpska.ui.club

import android.support.v7.app.AppCompatActivity
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.flights.FlightsModule
import cz.dmn.cpska.ui.leaderboard.LeaderboardModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.parceler.Parcels

@Module(includes = [BaseActivityModule::class, FlightsModule::class, LeaderboardModule::class])
abstract class ClubModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideClubParameter(activity: ClubActivity): Club = Parcels.unwrap(activity.intent.getParcelableExtra(ClubConstants.EXTRA_CLUB))
    }

    @Binds
    abstract fun bindActivity(activity: ClubActivity): AppCompatActivity
}