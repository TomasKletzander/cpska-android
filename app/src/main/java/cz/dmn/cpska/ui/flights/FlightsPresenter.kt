package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.PagedDataSource
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BasePagedDataPresenter
import javax.inject.Inject

@ActivityScope
class FlightsPresenter @Inject constructor(dataSource: PagedDataSource<FlightData>)
    : BasePagedDataPresenter<FlightData, FlightsMvp.View>(dataSource), FlightsMvp.Presenter