package cz.dmn.cpska.mvp

import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import io.reactivex.disposables.Disposable

open class BasePagedDataPresenter<in D, V: PagedDataView<D>>(private val interactor: BasePagedDataInteractor<List<D>>) : PagedDataPresenter<D, V>, BaseMvpPresenter<V>() {
    private var loading = false
    private var requestNextPageDisposable: Disposable? = null

    override fun attachView(view: V) {
        super.attachView(view)
        reset()
        loadNextPage()
        requestNextPageDisposable = view.requestNextPage.subscribe {
            if (!loading) {
                loadNextPage()
            }
        }
    }

    override fun detachView() {
        requestNextPageDisposable?.dispose()
        requestNextPageDisposable = null
        super.detachView()
    }

    override fun reset() {
        view?.clear()
        interactor.page = 0
        interactor.unsubscribe()
        loading = false
    }

    override fun loadNextPage() {
        if (loading) return
        loading = true
        interactor.execute(object : BaseInteractorSubscriber<List<D>>() {
            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(t: List<D>) {
                view?.apply {
                    addPage(t)
                    loading = false
                }
                ++interactor.page
                loading = false
            }

            override fun onError(e: Throwable) {
                view?.apply {
                    loading = false
                    showError(e.localizedMessage)
                }
            }
        })
    }
}