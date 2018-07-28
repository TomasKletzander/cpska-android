package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class FlightDetailsInteractor @Inject constructor(private val dataManager: DataManager) : BaseInteractor<FlightDetails>() {
    var flightId = 0
    override fun buildInteractorObservable() = dataManager.getFlightDetails(flightId)
}