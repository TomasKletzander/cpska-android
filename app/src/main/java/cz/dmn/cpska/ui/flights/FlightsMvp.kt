package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView

interface FlightsMvp {

    interface View: MvpView {
        fun show(flights: List<FlightData>)
    }

    interface Presenter: MvpPresenter<View> {
        fun load()
    }
}