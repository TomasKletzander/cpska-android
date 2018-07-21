package cz.dmn.cpska.mvp

import android.support.annotation.NonNull
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.PagedDataSource
import io.reactivex.Scheduler
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import org.junit.BeforeClass
import org.mockito.Mockito.reset
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class BasePagedDataPresenterTest {

    //@Rule @JvmField val rule = RxSchedulersOverrideRule()
    @Mock lateinit var dataSource: PagedDataSource<Data>
    @Mock lateinit var view: PagedDataView<Data>
    lateinit var presenter: BasePagedDataPresenter<Data, PagedDataView<Data>>

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
        }
    }

    @Before
    fun setUp() {
        presenter = BasePagedDataPresenter(dataSource)
    }

    @Test
    fun shouldLoadFirstAndSubsequentPage() {
        //  Prepare
        val dataSubject = PublishSubject.create<List<Data>>()
        whenever(dataSource.getPage(anyInt())).thenReturn(dataSubject)
        val nextPageSubject = PublishSubject.create<Any>()
        whenever(view.requestNextPage).thenReturn(nextPageSubject)

        //  Should load first page immediately
        presenter.attachView(view)
        verify(dataSource).getPage(0)
        verifyNoMoreInteractions(dataSource)
        verify(view).requestNextPage
        verify(view).clear()
        verify(view).loading = true
        verifyNoMoreInteractions(view)
        reset(view)

        //  Should show received data
        val data = emptyList<Data>()
        dataSubject.onNext(data)
        verify(view).loading = false
        verify(view).addPage(data)
        verifyNoMoreInteractions(view)
        reset(view)

        //  Should request next page upon view signal
        nextPageSubject.onNext(Any())
        verify(dataSource).getPage(1)
        verifyNoMoreInteractions(dataSource)
        verify(view, never()).clear()
        verify(view).loading = true
        verifyNoMoreInteractions(view)
        reset(view)

        //  Should ignore request for another page while load in progress
        nextPageSubject.onNext(Any())
        verifyNoMoreInteractions(dataSource)
        verifyNoMoreInteractions(view)
    }

    class Data
}