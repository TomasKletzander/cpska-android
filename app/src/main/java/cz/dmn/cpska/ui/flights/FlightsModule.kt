package cz.dmn.cpska.ui.flights

import dagger.Binds
import dagger.Module

@Module
interface FlightsModule {

    @Binds
    fun bindView(coordinator: FlightsCoordinator): FlightsMvp.View

    @Binds
    fun bindPresenter(presenter: FlightsPresenter): FlightsMvp.Presenter
}