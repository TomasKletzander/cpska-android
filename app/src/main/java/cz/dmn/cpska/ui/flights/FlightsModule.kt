package cz.dmn.cpska.ui.flights

import android.widget.ImageView
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.ui.ItemClickListener
import dagger.Binds
import dagger.Module

@Module
interface FlightsModule {

    @Binds
    fun bindView(coordinator: FlightsCoordinator): FlightsMvp.View

    @Binds
    fun bindPresenter(presenter: FlightsPresenter): FlightsMvp.Presenter

    @Binds
    fun bindFlightClickListener(coordinator: FlightsCoordinator): ItemClickListener<FlightData>
}