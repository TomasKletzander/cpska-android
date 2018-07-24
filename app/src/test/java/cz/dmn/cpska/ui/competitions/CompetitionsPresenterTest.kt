package cz.dmn.cpska.ui.competitions

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.LoadCompetitionsInteractor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompetitionsPresenterTest {

    lateinit var presenter: CompetitionsPresenter
    @Mock lateinit var interactor: LoadCompetitionsInteractor
    @Mock lateinit var view: CompetitionsMvp.View
    @Captor lateinit var subscriberCaptor: ArgumentCaptor<BaseInteractorSubscriber<List<Competition>>>

    @Before
    fun setUp() {
        presenter = CompetitionsPresenter(interactor)
    }

    @Test
    fun load() {

        presenter.attachView(view)
        verifyNoMoreInteractions(view)
        verify(interactor).execute(subscriberCaptor.capture())
        verifyNoMoreInteractions(interactor)

        val subscriber = subscriberCaptor.value
        val data = emptyList<Competition>()
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
    fun loadFailure() {
        presenter.attachView(view)
        verify(interactor).execute(subscriberCaptor.capture())
        val subscriber = subscriberCaptor.value
        subscriber.onStart()
        subscriber.onError(Throwable())
        verify(view).error(anyString())
    }
}