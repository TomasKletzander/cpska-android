package cz.dmn.cpska.data

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.source.ClubsHtmlDataSource
import cz.dmn.cpska.data.api.source.FlightHtmlDataSource
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class DataManager @Inject constructor(
    private val flightDataSource: FlightHtmlDataSource,
    private val clubsDataSource: ClubsHtmlDataSource
) {
    fun getFlights(page: Int): Observable<List<FlightData>> = flightDataSource.getPage(page)
    fun getClubs(): Observable<List<Club>> = clubsDataSource.getClubs()
}