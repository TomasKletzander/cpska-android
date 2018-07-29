package cz.dmn.cpska.mvp

import android.os.Bundle

interface MvpPresenter<in V: MvpView, out PS: PresenterState<*>> {
    fun attachView(view: V)
    fun detachView()
    val state: PS
    fun saveState(outState: Bundle)
    fun restoreState(inState: Bundle)
}