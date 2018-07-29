package cz.dmn.cpska.ui.home

import android.support.v7.app.AppCompatActivity
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.clubs.ClubsModule
import cz.dmn.cpska.ui.flights.FlightsModule
import cz.dmn.cpska.ui.leaderboard.LeaderboardModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class, LeaderboardModule::class, FlightsModule::class, ClubsModule::class])
abstract class HomeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideContent(activity: HomeActivity) = activity.binding.content

        @Provides
        @JvmStatic
        fun provideNavigation(activity: HomeActivity) = activity.binding.navigation
    }

    @Binds
    abstract fun bindActivity(activity: HomeActivity): AppCompatActivity
}