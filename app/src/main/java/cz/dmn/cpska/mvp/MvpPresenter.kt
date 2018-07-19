package cz.dmn.cpska.mvp

interface MvpPresenter<V: MvpView> {
    fun attachView(view: V)
    fun detachView()
}