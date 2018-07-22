package cz.dmn.cpska.data

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.source.FlightHtmlDataSource
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class DataManager @Inject constructor(private val flightDataSource: FlightHtmlDataSource) {

    fun getFlights(page: Int): Observable<List<FlightData>> = flightDataSource.getPage(page)
}