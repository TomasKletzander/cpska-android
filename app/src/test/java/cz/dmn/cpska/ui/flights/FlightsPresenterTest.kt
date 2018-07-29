package cz.dmn.cpska.ui.flights

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.interactors.FlightsInteractor
import cz.dmn.cpska.navigators.FlightNavigator
import cz.dmn.cpska.util.FavoriteClubsManager
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlightsPresenterTest {

    lateinit var presenter: FlightsPresenter
    @Mock lateinit var interactor: FlightsInteractor
    @Mock lateinit var flightNavigator: FlightNavigator
    @Mock lateinit var view: FlightsMvp.View
    @Mock lateinit var res: Resources
    @Mock lateinit var favoriteClubsManager: FavoriteClubsManager
    @Mock lateinit var presenyterState: FlightsPresenterState
    val openFlightSubject = PublishSubject.create<FlightData>()
    val requestNextPageSubject = PublishSubject.create<Any>()
    val requestRefreshSubject = PublishSubject.create<Any>()
    val eventsSubject = PublishSubject.create<FavoriteClubsManager.Event>()
    @Mock lateinit var data: FlightData

    @Before
    fun setUp() {
        whenever(view.requestOpenFlight).thenReturn(openFlightSubject)
        whenever(view.requestNextPage).thenReturn(requestNextPageSubject)
        whenever(view.requestRefresh).thenReturn(requestRefreshSubject)
        whenever(favoriteClubsManager.events).thenReturn(eventsSubject)
        whenever(res.getString(anyInt())).thenReturn("")
        presenter = FlightsPresenter(interactor, flightNavigator, res, favoriteClubsManager, presenyterState)
    }

    @Test
    fun testOpenFlight() {
        whenever(data.id).thenReturn(1)
        presenter.attachView(view)
        verify(view).requestRefresh
        verify(view).requestOpenFlight

        openFlightSubject.onNext(data)
        verify(flightNavigator).openFlight(data)
    }

    @Test
    fun testOpenFlightFail() {
        whenever(data.id).thenReturn(0)
        presenter.attachView(view)
        verify(view).requestRefresh
        verify(view).requestOpenFlight

        openFlightSubject.onNext(data)
        verify(view).warning("")
    }
}