package cz.dmn.cpska.ui.home

import android.app.Activity
import cz.dmn.cpska.databinding.CoordLeaderboardBinding
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.leaderboard.LeaderboardModule
import dagger.Module
import dagger.Provides

@Module(includes = [BaseActivityModule::class, LeaderboardModule::class])
class HomeModule {
    @Provides
    fun bindActivity(activity: HomeActivity): Activity = activity

    @Provides
    fun provideLeaderboardBinding(activity: HomeActivity): CoordLeaderboardBinding = activity.binding.leaderboard!!
}