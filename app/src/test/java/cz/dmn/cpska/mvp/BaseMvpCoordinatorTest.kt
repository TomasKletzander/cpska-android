package cz.dmn.cpska.mvp

import android.view.View
import com.nhaarman.mockito_kotlin.verify
import dagger.Lazy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseMvpCoordinatorTest {

    lateinit var baseMvpCoordinator: BaseMvpCoordinator<MvpView, MvpPresenter<MvpView>>
    @Mock lateinit var view: View
    @Mock lateinit var mvpView: MvpView
    @Mock lateinit var mvpPresenter: MvpPresenter<MvpView>

    @Before
    fun setUp() {
        baseMvpCoordinator = BaseMvpCoordinator()
        baseMvpCoordinator.presenterLazy = Lazy { mvpPresenter }
        baseMvpCoordinator.viewLazy = Lazy { mvpView }
    }

    @Test
    fun attach() {
        baseMvpCoordinator.attach(view)
        verify(mvpPresenter).attachView(mvpView)
    }

    @Test
    fun detach() {
        baseMvpCoordinator.detach(view)
        verify(mvpPresenter).detachView()
    }
}