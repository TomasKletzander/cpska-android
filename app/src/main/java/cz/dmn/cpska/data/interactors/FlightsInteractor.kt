package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BasePagedDataInteractor
import javax.inject.Inject

@PerActivity
class FlightsInteractor @Inject constructor(private val dataManager: DataManager) : BasePagedDataInteractor<FlightData>() {
    var clubId = 0
    override fun buildInteractorObservable() = dataManager.getFlights(clubId, page)
}