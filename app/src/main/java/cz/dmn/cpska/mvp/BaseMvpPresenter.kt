package cz.dmn.cpska.mvp

open class BaseMvpPresenter<V: MvpView> : MvpPresenter<V> {

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}