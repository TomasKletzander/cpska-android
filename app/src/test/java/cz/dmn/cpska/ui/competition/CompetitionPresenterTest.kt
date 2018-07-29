package cz.dmn.cpska.ui.competition

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.CompetitionDetailsInteractor
import cz.dmn.cpska.mvp.EmptyPresenterState
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompetitionPresenterTest {

    lateinit var presenter: CompetitionPresenter
    @Mock lateinit var competition: Competition
    @Mock lateinit var interactor: CompetitionDetailsInteractor
    @Mock lateinit var view: CompetitionMvp.View
    @Captor lateinit var subscriberCaptor: ArgumentCaptor<BaseInteractorSubscriber<CompetitionDetails>>
    @Mock lateinit var data: CompetitionDetails

    @Before
    fun setUp() {
        whenever(competition.id).thenReturn(1)
        presenter = CompetitionPresenter(competition, interactor, EmptyPresenterState())
    }

    @Test
    fun testHappyPath() {
        presenter.attachView(view)
        verifyNoMoreInteractions(view)
        verify(interactor).competitionId = 1
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
        verify(interactor).competitionId = 1
        verify(interactor).execute(subscriberCaptor.capture())
        verifyNoMoreInteractions(interactor)
        val subscriber = subscriberCaptor.value

        subscriber.onStart()
        verify(view).loading = true
        verifyNoMoreInteractions(view)

        subscriber.onError(Exception("An error text"))
        verify(view).loading = false
        verify(view).error("An error text")
        verifyNoMoreInteractions(view)

        presenter.detachView()
        verify(interactor).unsubscribe()
    }
}