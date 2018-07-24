package cz.dmn.cpska.data

import com.nhaarman.mockito_kotlin.verify
import cz.dmn.cpska.data.api.source.ClubsHtmlDataSource
import cz.dmn.cpska.data.api.source.CompetitionsDataSource
import cz.dmn.cpska.data.api.source.FlightsHtmlDataSource
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

    @Before
    fun setUp() {
        dataManager = DataManager(flightsDataSource, clubsDataSource, competitionsDataSource)
    }

    @Test
    fun getFlights() {
        dataManager.getFlights(1)
        verify(flightsDataSource).getPage(1)
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
}