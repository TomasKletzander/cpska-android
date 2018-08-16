package cz.dmn.cpska.data.interactors

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.cfg.Configuration
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConfigurationInteractorTest {

    lateinit var interactor: ConfigurationInteractor
    @Mock lateinit var dataManager: DataManager
    val testObserver = TestObserver.create<Configuration>()
    @Mock lateinit var data: Configuration
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        whenever(dataManager.getConfiguration()).thenReturn(Observable.just(data))
        interactor = ConfigurationInteractor(dataManager)
    }

    @Test
    fun test() {
        interactor.execute(testObserver)
        verify(dataManager).getConfiguration()
        verifyNoMoreInteractions(dataManager)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()
        testObserver.assertValue(data)
    }
}