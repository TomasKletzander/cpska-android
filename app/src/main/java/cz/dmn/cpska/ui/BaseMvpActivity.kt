package cz.dmn.cpska.ui

import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import javax.inject.Inject

open class BaseMvpActivity<V: MvpView, P: MvpPresenter<V>> : BaseActivity() {

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
}