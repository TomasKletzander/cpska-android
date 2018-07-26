package cz.dmn.cpska.ui.competition

import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView

interface CompetitionMvp {

    interface View: MvpView {
        var loading: Boolean
        fun show(competitionDetails: CompetitionDetails)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun load()
    }
}