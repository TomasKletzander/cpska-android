package cz.dmn.cpska.data

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.source.ClubsDataSource
import cz.dmn.cpska.data.api.source.CompetitionsDataSource
import cz.dmn.cpska.data.api.source.FlightsDataSource
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class DataManager @Inject constructor(
    private val flightsDataSource: FlightsDataSource,
    private val clubsDataSource: ClubsDataSource,
    private val competitionsDataSource: CompetitionsDataSource
) {
    fun getFlights(page: Int): Observable<List<FlightData>> = flightsDataSource.getPage(page)
    fun getClubs(): Observable<List<Club>> = clubsDataSource.getClubs()
    fun getCompetitions(): Observable<List<Competition>> = competitionsDataSource.getCompetitions()
}