package cz.dmn.cpska.ui.leaderboard

import dagger.Binds
import dagger.Module

@Module
interface LeaderboardModule {

    @Binds
    fun bindView(coordinator: LeaderboardCoordinator): LeaderboardMvp.View

    @Binds
    fun bindPresenter(presenter: LeaderboardPresenter): LeaderboardMvp.Presenter
}