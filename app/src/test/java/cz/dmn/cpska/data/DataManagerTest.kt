package cz.dmn.cpska.data

import com.nhaarman.mockito_kotlin.verify
import cz.dmn.cpska.data.api.source.ClubsHtmlDataSource
import cz.dmn.cpska.data.api.source.CompetitionDetailsDataSource
import cz.dmn.cpska.data.api.source.CompetitionsDataSource
import cz.dmn.cpska.data.api.source.ConfigurationDataSource
import cz.dmn.cpska.data.api.source.FlightDetailsDataSource
import cz.dmn.cpska.data.api.source.FlightsHtmlDataSource
import cz.dmn.cpska.data.api.source.FogglesDataSource
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    lateinit var dataManager: DataManager
    @Mock lateinit var flightsDataSource: FlightsHtmlDataSource
    @Mock lateinit var clubsDataSource: ClubsHtmlDataSource
    @Mock lateinit var competitionsDataSource: CompetitionsDataSource
    @Mock lateinit var competitionDetailsDataSource: CompetitionDetailsDataSource
    @Mock lateinit var flightDetailsDataSource: FlightDetailsDataSource
    @Mock lateinit var configurationDataSource: ConfigurationDataSource
    @Mock lateinit var fogglesDataSource: FogglesDataSource

    @Before
    fun setUp() {
        dataManager = DataManager(
                flightsDataSource,
                clubsDataSource,
                competitionsDataSource,
                competitionDetailsDataSource,
                flightDetailsDataSource,
                configurationDataSource,
                fogglesDataSource
        )
    }

    @Test
    fun getFlights() {
        dataManager.getFlights(0,1)
        verify(flightsDataSource).getPage(0,1)
    }

    @Test
    fun getClubs() {
        dataManager.getClubs()
        verify(clubsDataSource).getClubs()
    }

    @Test
    fun getCompetitions() {
        dataManager.getCompetitions()
        verify(competitionsDataSource).getCompetitions()
    }

    @Test
    fun getCompetitionDetails() {
        dataManager.getCompetitionDetails(1)
        verify(competitionDetailsDataSource).getCompetitionDetails(1)
    }

    @Test
    fun getFlightDetails() {
        dataManager.getFlightDetails(1)
        verify(flightDetailsDataSource).getFlightDetails(1)
    }

    @Test
    fun getConfiguration() {
        dataManager.getConfiguration()
        verify(configurationDataSource).getConfiguration()
    }

    @Test
    fun getFoggles() {
        dataManager.getFoggles()
        verify(fogglesDataSource).getFoggles()
    }
}