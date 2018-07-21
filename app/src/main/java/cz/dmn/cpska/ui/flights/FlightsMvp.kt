package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.mvp.PagedDataPresenter
import cz.dmn.cpska.mvp.PagedDataView

interface FlightsMvp {
    interface View: PagedDataView<FlightData>
    interface Presenter: PagedDataPresenter<FlightData, View>
}