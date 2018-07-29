package cz.dmn.cpska.ui.flight.info

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

interface FlightInfoMvp {

    interface View: MvpView {
        fun show(flightDetails: FlightDetails)
    }

    interface Presenter: MvpPresenter<View, PresenterState<*>> {
        fun show(flightDetails: FlightDetails)
    }
}