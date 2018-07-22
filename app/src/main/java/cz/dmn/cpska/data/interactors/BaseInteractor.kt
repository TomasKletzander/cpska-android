package cz.dmn.cpska.data.interactors

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseInteractor<D> {

    private val disposables = CompositeDisposable()

    fun <O>execute(disposableObserver: O) where O: Disposable, O: Observer<D> {
        disposables.add(buildInteractorObservable()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver))
    }

    protected abstract fun buildInteractorObservable(): Observable<D>

    private fun <X> applySchedulers(): ObservableTransformer<X, X> = ObservableTransformer {
        it.subscribeOn(Schedulers.io())
        it.unsubscribeOn(Schedulers.io())
        it.observeOn(AndroidSchedulers.mainThread())
        it
    }

    fun unsubscribe() {
        disposables.clear()
    }
}