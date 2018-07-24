package cz.dmn.cpska.data.interactors

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.Club
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadClubsInteractorTest {

    lateinit var interactor: LoadClubsInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<List<Club>>()
    val data = emptyList<Club>()
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getClubs()).thenReturn(Observable.just(data))
        interactor = LoadClubsInteractor(dataManager)
    }

    @Test
    fun test() {
        interactor.execute(testObserver)
        verify(dataManager).getClubs()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}