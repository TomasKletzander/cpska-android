package cz.dmn.cpska.data.interactors

import io.reactivex.observers.DisposableObserver

abstract class BaseInteractorSubscriber<D> : DisposableObserver<D>() {

    override public fun onStart() {
    }

    override fun onNext(t: D) {
    }

    override fun onError(e: Throwable) {
    }

    override fun onComplete() {
    }
}