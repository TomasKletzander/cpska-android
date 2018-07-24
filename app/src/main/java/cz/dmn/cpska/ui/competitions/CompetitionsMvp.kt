package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import io.reactivex.subjects.Subject

interface CompetitionsMvp {

    interface View: MvpView {

        var loading: Boolean
        val refreshSubject: Subject<Any>
        fun show(competitions: List<Competition>)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View> {

        fun load()
    }
}