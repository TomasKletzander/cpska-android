package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.FlightDetails
import io.reactivex.Observable

interface FlightDetailsDataSource {
    fun getFlightDetails(id: Int): Observable<FlightDetails>
}