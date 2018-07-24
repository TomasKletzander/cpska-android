package cz.dmn.cpska.data.interactors

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.Competition
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadCompetitionsInteractorTest {

    lateinit var interactor: LoadCompetitionsInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<List<Competition>>()
    val data = emptyList<Competition>()
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getCompetitions()).thenReturn(Observable.just(data))
        interactor = LoadCompetitionsInteractor(dataManager)
    }

    @Test
    fun buildInteractorObservable() {
        interactor.execute(testObserver)
        verify(dataManager).getCompetitions()
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}