package cz.dmn.cpska.data.interactors

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.CompetitionDetails
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoadCompetitionDetailsInteractorTest {

    lateinit var interactor: LoadCompetitionDetailsInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<CompetitionDetails>()
    @Mock lateinit var data: CompetitionDetails
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getCompetitionDetails(1)).thenReturn(Observable.just(data))
        interactor = LoadCompetitionDetailsInteractor(dataManager)
    }

    @Test
    fun test() {
        interactor.competitionId = 1
        interactor.execute(testObserver)
        verify(dataManager).getCompetitionDetails(1)
        verifyNoMoreInteractions(dataManager)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}