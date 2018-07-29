package cz.dmn.cpska.data.interactors

import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.FlightData
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlightsInteractorTest {

    lateinit var interactor: FlightsInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<List<FlightData>>()
    val data = listOf<FlightData>()
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getFlights(anyInt(), anyInt())).thenReturn(Observable.just(data))
        interactor = FlightsInteractor(dataManager)
    }

    @Test
    fun shouldCallApi() {
        interactor.apply {
            clubId = 1
            page = 2
        }.execute(testObserver)

        verify(dataManager).getFlights(1, 2)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}