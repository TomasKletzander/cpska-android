package cz.dmn.cpska.ui.flight

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView

interface FlightMvp {

    interface View: MvpView {
        var loading: Boolean
        fun show(flightDetails: FlightDetails)
        fun error(message: String)
    }

    interface Presenter: MvpPresenter<View> {
        fun load()
    }
}