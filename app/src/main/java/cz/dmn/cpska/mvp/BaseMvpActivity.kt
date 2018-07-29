package cz.dmn.cpska.mvp

import android.os.Bundle
import cz.dmn.cpska.ui.BaseActivity
import javax.inject.Inject

open class BaseMvpActivity<V: MvpView, P: MvpPresenter<V, PresenterState<*>>> : BaseActivity() {

    @Inject lateinit var presenter: P
    @Inject lateinit var view: V

    override fun onStart() {
        super.onStart()
        presenter.attachView(view)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
            presenter.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.saveState(outState)
    }
}