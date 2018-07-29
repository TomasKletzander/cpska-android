package cz.dmn.cpska.ui.leaderboard

import cz.dmn.cpska.data.api.leaderboard.LeaderboardRow
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

interface LeaderboardMvp {

    interface View : MvpView {
        fun show(rows: List<LeaderboardRow>)
    }

    interface Presenter : MvpPresenter<View, PresenterState<*>> {
        fun load()
    }
}