package cz.dmn.cpska.mvp

import android.view.View
import com.squareup.coordinators.Coordinator
import javax.inject.Inject

class BaseMvpCoordinator<V: MvpView, P: MvpPresenter<V>> : Coordinator() {

    @Inject lateinit var presenter: P
    @Inject lateinit var view: V

    override fun attach(view: View) {
        presenter.attachView(this.view)
    }

    override fun detach(view: View) {
        presenter.detachView()
    }
}