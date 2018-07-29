package cz.dmn.cpska.ui.splash

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.navigators.HomeNavigator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTest {

    lateinit var presenter: SplashPresenter
    @Mock lateinit var homeNavigator: HomeNavigator
    @Mock lateinit var app: CpsApp
    @Mock lateinit var view: SplashMvp.View
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        presenter = SplashPresenter(homeNavigator, app)
    }

    @Test
    fun testSuccess() {
        whenever(app.initialize()).thenReturn(Observable.just(true))
        presenter.attachView(view)
        verify(app).initialize()
        verify(homeNavigator).openHome()
        verify(view).close()
    }

    @Test
    fun testError() {
        whenever(app.initialize()).thenReturn(Observable.error(Exception("Some error")))
        presenter.attachView(view)
        verify(view).error(anyInt())
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(homeNavigator)
    }
}