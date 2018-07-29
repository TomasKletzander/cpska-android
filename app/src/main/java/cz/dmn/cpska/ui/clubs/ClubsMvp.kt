package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.db.FavoriteClubs
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState
import io.reactivex.subjects.Subject

interface ClubsMvp {

    interface View: MvpView {

        val requestOpenClub: Subject<Club>
        val toggleFavoriteClub: Subject<Club>
        fun show(clubs: List<Pair<Club, Boolean>>)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View, PresenterState<*>>
}