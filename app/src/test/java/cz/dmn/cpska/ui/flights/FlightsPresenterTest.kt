package cz.dmn.cpska.ui.flights

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.interactors.FlightsInteractor
import cz.dmn.cpska.navigators.FlightNavigator
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlightsPresenterTest {

    lateinit var presenter: FlightsPresenter
    @Mock lateinit var interactor: FlightsInteractor
    @Mock lateinit var flightNavigator: FlightNavigator
    @Mock lateinit var view: FlightsMvp.View
    val openFlightSubject = PublishSubject.create<FlightData>()
    val requestNextPageSubject = PublishSubject.create<Any>()
    val requestRefreshSubject = PublishSubject.create<Any>()
    @Mock lateinit var data: FlightData

    @Before
    fun setUp() {
        whenever(view.requestOpenFlight).thenReturn(openFlightSubject)
        whenever(view.requestNextPage).thenReturn(requestNextPageSubject)
        whenever(view.requestRefresh).thenReturn(requestRefreshSubject)
        presenter = FlightsPresenter(interactor, flightNavigator)
    }

    @Test
    fun testOpenFlight() {
        presenter.attachView(view)
        verify(view).requestRefresh
        verify(view).requestOpenFlight

        openFlightSubject.onNext(data)
        verify(flightNavigator).openFlight(data)
    }
}