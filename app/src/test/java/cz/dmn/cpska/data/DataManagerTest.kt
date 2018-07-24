package cz.dmn.cpska.data

import com.nhaarman.mockito_kotlin.verify
import cz.dmn.cpska.data.api.source.ClubsHtmlDataSource
import cz.dmn.cpska.data.api.source.FlightHtmlDataSource
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    lateinit var dataManager: DataManager
    @Mock lateinit var flightDataSource: FlightHtmlDataSource
    @Mock lateinit var clubsDataSource: ClubsHtmlDataSource

    @Before
    fun setUp() {
        dataManager = DataManager(flightDataSource, clubsDataSource)
    }

    @Test
    fun getFlights() {
        dataManager.getFlights(1)
        verify(flightDataSource).getPage(1)
    }

    @Test
    fun getClubs() {
        dataManager.getClubs()
        verify(clubsDataSource).getClubs()
    }
}