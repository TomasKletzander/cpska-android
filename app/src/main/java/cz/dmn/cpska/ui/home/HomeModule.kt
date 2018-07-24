package cz.dmn.cpska.ui.home

import android.app.Activity
import cz.dmn.cpska.databinding.CompetitionsRowBinding
import cz.dmn.cpska.databinding.CoordCompetitionsBinding
import cz.dmn.cpska.databinding.CoordFlightsBinding
import cz.dmn.cpska.databinding.CoordLeaderboardBinding
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.competitions.CompetitionsModule
import cz.dmn.cpska.ui.flights.FlightsModule
import cz.dmn.cpska.ui.leaderboard.LeaderboardModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class, LeaderboardModule::class, FlightsModule::class, CompetitionsModule::class])
class HomeModule {
    @Provides
    fun bindActivity(activity: HomeActivity): Activity = activity

    @Provides
    fun provideLeaderboardBinding(activity: HomeActivity): CoordLeaderboardBinding = activity.binding.leaderboard!!

    @Provides
    fun provideFlightsBinding(activity: HomeActivity): CoordFlightsBinding = activity.binding.flights!!

    @Provides
    fun provideCompetitionsBinding(activity: HomeActivity): CoordCompetitionsBinding = activity.binding.competitions!!
}