package cz.dmn.cpska.ui.flight.info

import dagger.Binds
import dagger.Module

@Module
interface FlightInfoModule {

    @Binds
    fun bindView(coordinator: FlightInfoCoordinator): FlightInfoMvp.View

    @Binds
    fun bindPresenter(presenter: FlightInfoPresenter): FlightInfoMvp.Presenter
}