package cz.dmn.cpska.mvp

import android.view.View
import com.squareup.coordinators.Coordinator
import dagger.Lazy
import javax.inject.Inject

open class BaseMvpCoordinator<V: MvpView, P: MvpPresenter<V>> : Coordinator() {

    @Inject lateinit var presenterLazy: Lazy<P>
    @Inject lateinit var viewLazy: Lazy<V>
    val presenter by lazy { presenterLazy.get() }
    val view by lazy { viewLazy.get() }

    override fun attach(view: View) {
        presenter.attachView(this.view)
    }

    override fun detach(view: View) {
        presenter.detachView()
    }
}