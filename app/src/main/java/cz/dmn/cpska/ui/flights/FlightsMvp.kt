package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.mvp.PagedDataPresenter
import cz.dmn.cpska.mvp.PagedDataView
import io.reactivex.subjects.Subject

interface FlightsMvp {
    interface View: PagedDataView<FlightData> {
        val requestRefresh: Subject<Any>
        val requestOpenFlight: Subject<FlightData>
    }

    interface Presenter: PagedDataPresenter<FlightData, View>
}