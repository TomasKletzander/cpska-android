package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.FlightData
import io.reactivex.Observable

interface FlightsDataSource {
    fun getPage(clubId: Int, pageIndex: Int): Observable<List<FlightData>>
}