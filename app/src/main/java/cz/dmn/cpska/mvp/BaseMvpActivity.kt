package cz.dmn.cpska.mvp

import cz.dmn.cpska.ui.BaseActivity
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