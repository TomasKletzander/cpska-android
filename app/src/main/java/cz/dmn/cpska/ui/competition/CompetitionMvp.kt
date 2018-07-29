package cz.dmn.cpska.ui.competition

import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

interface CompetitionMvp {

    interface View: MvpView {
        var loading: Boolean
        fun show(competitionDetails: CompetitionDetails)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View, PresenterState<*>> {
        fun load()
    }
}