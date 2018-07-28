package cz.dmn.cpska.ui.home

import android.app.Activity
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.clubs.ClubsModule
import cz.dmn.cpska.ui.flights.FlightsModule
import cz.dmn.cpska.ui.leaderboard.LeaderboardModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class, LeaderboardModule::class, FlightsModule::class, ClubsModule::class])
interface HomeModule {

    @Binds
    fun bindActivity(activity: HomeActivity): Activity
}