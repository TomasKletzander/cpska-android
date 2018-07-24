package cz.dmn.cpska.mvp

import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.reset


@RunWith(MockitoJUnitRunner::class)
class BasePagedDataPresenterTest {

    @Rule @JvmField val rule = RxSchedulersOverrideRule()
    @Mock lateinit var view: PagedDataView<Data>
    @Mock lateinit var interactor: BasePagedDataInteractor<Data>
    lateinit var presenter: BasePagedDataPresenter<Data, PagedDataView<Data>>
    @Captor lateinit var subscriberCaptor: ArgumentCaptor<BaseInteractorSubscriber<List<Data>>>
    val nextPageSubject = PublishSubject.create<Any>()

    @Before
    fun setUp() {
        whenever(view.requestNextPage).thenReturn(nextPageSubject)
        presenter = BasePagedDataPresenter(interactor)
    }

    @Test
    fun shouldLoadFirstAndSubsequentPage() {
        //  Prepare

        //  Should load first page immediately
        presenter.attachView(view)
        verify(interactor).page = 0
        verify(interactor).execute(subscriberCaptor.capture())
        val page1Subscriber: BaseInteractorSubscriber<List<Data>> = subscriberCaptor.value
        verify(view).requestNextPage
        verify(view).clear()
        verifyNoMoreInteractions(view)
        reset(view)

        //  Should indicate loading
        page1Subscriber.onStart()
        verify(view).loading = true
        verifyNoMoreInteractions(view)
        reset(view)

        //  Should show received data
        val data = emptyList<Data>()
        page1Subscriber.onNext(data)
        verify(view).loading = false
        verify(view).addPage(data)
        verifyNoMoreInteractions(view)
        reset(view)
        verify(interactor).page = 1
        reset(interactor)

        //  Should request next page upon view signal
        nextPageSubject.onNext(Any())
        verify(interactor).execute(subscriberCaptor.capture())
        val page2Subscriber: BaseInteractorSubscriber<List<Data>> = subscriberCaptor.value
        verifyNoMoreInteractions(interactor)

        //  Should ignore request for another page while load in progress
        nextPageSubject.onNext(Any())
        verifyNoMoreInteractions(interactor)
        verifyNoMoreInteractions(view)

        //  Should unsubscribe from interactor
        presenter.detachView()
        verify(interactor).unsubscribe()
    }

    class Data
}