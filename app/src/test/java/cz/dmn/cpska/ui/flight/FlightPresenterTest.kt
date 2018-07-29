package cz.dmn.cpska.ui.flight

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.data.api.Plane
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.FlightDetailsInteractor
import cz.dmn.cpska.ui.flight.info.FlightInfoPresenter
import io.reactivex.observers.TestObserver
import org.joda.time.LocalDate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlightPresenterTest {

    lateinit var presenter: FlightPresenter
    @Mock lateinit var interactor: FlightDetailsInteractor
    @Mock lateinit var view: FlightMvp.View
    @Captor lateinit var subscriberCaptor: ArgumentCaptor<BaseInteractorSubscriber<FlightDetails>>
    @Mock lateinit var data: FlightDetails
    @Mock lateinit var flightInfoPresenter: FlightInfoPresenter
    @Mock lateinit var presenterState: FlightPresenterState
    val flight = FlightData(1, LocalDate.now(), "cz", 10, User(1, "user"),
            100f, 90f, Club(1, "Medlanky"), Plane("Astir"))

    @Before
    fun setUp() {
        presenter = FlightPresenter(interactor, flight, flightInfoPresenter, presenterState)
    }

    @Test
    fun testHappyPath() {
        presenter.attachView(view)
        verifyNoMoreInteractions(view)
        verify(interactor).flightId = 1
        verify(interactor).execute(subscriberCaptor.capture())
        verifyNoMoreInteractions(interactor)
        val subscriber = subscriberCaptor.value

        subscriber.onStart()
        verify(view).loading = true
        verifyNoMoreInteractions(view)

        subscriber.onNext(data)
        verify(view).loading = false
        verify(view).show(data)
        verifyNoMoreInteractions(view)

        presenter.detachView()
        verify(interactor).unsubscribe()
    }

    @Test
    fun testErrorPath() {
        presenter.attachView(view)
        verifyNoMoreInteractions(view)
        verify(interactor).flightId = 1
        verify(interactor).execute(subscriberCaptor.capture())
        verifyNoMoreInteractions(interactor)
        val subscriber = subscriberCaptor.value

        subscriber.onStart()
        verify(view).loading = true
        verifyNoMoreInteractions(view)

        subscriber.onError(Exception("Some error"))
        verify(view).loading = false
        verify(view).error("Some error")
        verifyNoMoreInteractions(view)
    }
}