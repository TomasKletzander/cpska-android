package cz.dmn.cpska.ui.flight.info

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView

interface FlightInfoMvp {

    interface View: MvpView {
        fun show(flightDetails: FlightDetails)
    }

    interface Presenter: MvpPresenter<View> {
        fun show(flightDetails: FlightDetails)
    }
}