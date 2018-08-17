package cz.dmn.cpska.data

import cz.dmn.cpska.data.api.source.ClubsDataSource
import cz.dmn.cpska.data.api.source.CompetitionDetailsDataSource
import cz.dmn.cpska.data.api.source.CompetitionsDataSource
import cz.dmn.cpska.data.api.source.ConfigurationDataSource
import cz.dmn.cpska.data.api.source.FlightDetailsDataSource
import cz.dmn.cpska.data.api.source.FlightsDataSource
import cz.dmn.cpska.data.api.source.FogglesDataSource
import cz.dmn.cpska.di.PerApplication
import javax.inject.Inject

@PerApplication
class DataManager @Inject constructor(
    private val flightsDataSource: FlightsDataSource,
    private val clubsDataSource: ClubsDataSource,
    private val competitionsDataSource: CompetitionsDataSource,
    private val competitionDetailsDataSource: CompetitionDetailsDataSource,
    private val flightDetailsDataSource: FlightDetailsDataSource,
    private val configurationDataSource: ConfigurationDataSource,
    private val fogglesDataSource: FogglesDataSource
) {
    fun getFlights(clubId: Int, page: Int) = flightsDataSource.getPage(clubId, page)
    fun getClubs() = clubsDataSource.getClubs()
    fun getCompetitions() = competitionsDataSource.getCompetitions()
    fun getCompetitionDetails(id: Int) = competitionDetailsDataSource.getCompetitionDetails(id)
    fun getFlightDetails(id: Int) = flightDetailsDataSource.getFlightDetails(id)
    fun getConfiguration() = configurationDataSource.getConfiguration()
    fun getFoggles() = fogglesDataSource.getFoggles()
}