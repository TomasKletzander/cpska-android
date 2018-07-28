package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView

interface ClubsMvp {

    interface View: MvpView {

        fun show(clubs: List<Club>)
    }

    interface Presenter: MvpPresenter<View>
}