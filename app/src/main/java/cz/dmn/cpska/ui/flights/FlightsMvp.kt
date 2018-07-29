package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.mvp.PagedDataPresenter
import cz.dmn.cpska.mvp.PagedDataView
import io.reactivex.subjects.Subject

interface FlightsMvp {

    interface View: PagedDataView<Pair<FlightData, Boolean>> {
        val requestRefresh: Subject<Any>
        val requestOpenFlight: Subject<FlightData>
        fun warning(message: String)
    }

    interface Presenter: PagedDataPresenter<FlightData, Pair<FlightData, Boolean>, View> {
        var filterClub: Club?
    }
}