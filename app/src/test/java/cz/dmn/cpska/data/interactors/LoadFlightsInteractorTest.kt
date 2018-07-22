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
class LoadFlightsInteractorTest {

    lateinit var interactor: LoadFlightsInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<List<FlightData>>()
    val data = listOf<FlightData>()
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getFlights(anyInt())).thenReturn(Observable.just(data))
        interactor = LoadFlightsInteractor(dataManager)
    }

    @Test
    fun shouldCallApi() {
        interactor.apply {
            page = 2
        }.execute(testObserver)

        verify(dataManager).getFlights(2)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}