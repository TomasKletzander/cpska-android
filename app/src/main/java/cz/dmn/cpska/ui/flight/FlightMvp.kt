package cz.dmn.cpska.ui.flight

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

interface FlightMvp {

    interface View: MvpView {
        var loading: Boolean
        fun show(flightDetails: FlightDetails)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View, PresenterState<*>> {
        fun load()
    }
}