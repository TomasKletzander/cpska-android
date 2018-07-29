package cz.dmn.cpska.ui.flight.info

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import cz.dmn.cpska.data.api.FlightDetails
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FlightInfoPresenterTest {

    lateinit var presenter: FlightInfoPresenter
    @Mock lateinit var view: FlightInfoMvp.View
    @Mock lateinit var data: FlightDetails

    @Before
    fun setUp() {
        presenter = FlightInfoPresenter()
    }

    @Test
    fun showBeforeAttach() {
        presenter.attachView(view)
        verifyNoMoreInteractions(view)
        presenter.show(data)
        verify(view).show(data)
    }

    @Test
    fun showAfterAttach() {
        presenter.show(data)
        presenter.attachView(view)
        verify(view).show(data)
    }
}