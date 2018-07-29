package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState
import io.reactivex.subjects.Subject

interface CompetitionsMvp {

    interface View : MvpView {
        fun show(competitions: List<Competition>)
    }

    interface Presenter : MvpPresenter<View, PresenterState<*>>
}