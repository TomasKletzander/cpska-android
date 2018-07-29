package cz.dmn.cpska.mvp

import android.os.Bundle

abstract class BaseMvpPresenter<V: MvpView, PS: PresenterState<*>> : MvpPresenter<V, PS> {

    protected var view: V? = null

    override fun saveState(outState: Bundle) {
        outState.putParcelable(stateKey, state.save())
    }

    override fun restoreState(inState: Bundle) {
        state.restore(inState.getParcelable(stateKey))
    }

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    val stateKey = javaClass.canonicalName + ".state"
}