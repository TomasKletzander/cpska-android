package cz.dmn.cpska.mvp

import android.app.ProgressDialog.show
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import io.reactivex.disposables.Disposable

abstract class BasePagedDataPresenter<in ID, VD, V: PagedDataView<VD>>(private val interactor: BasePagedDataInteractor<ID>) : PagedDataPresenter<ID, VD, V>, BaseMvpPresenter<V>() {
    private var loading = false
    private var requestNextPageDisposable: Disposable? = null
    private val data = mutableListOf<ID>()

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
        interactor.unsubscribe()
        super.detachView()
    }

    override fun reset() {
        view?.clear()
        data.clear()
        interactor.page = 0
        interactor.unsubscribe()
        loading = false
    }

    override fun loadNextPage() {
        if (loading) return
        loading = true
        interactor.execute(object : BaseInteractorSubscriber<List<ID>>() {
            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(t: List<ID>) {
                data.addAll(t)
                view?.apply {
                    show(data.map { mapData(it) })
                    loading = false
                }
                ++interactor.page
                loading = false
            }

            override fun onError(e: Throwable) {
                view?.apply {
                    loading = false
                    error(e.localizedMessage)
                }
            }
        })
    }

    fun refresh() {
        view?.show(data.map { mapData(it) })
    }

    abstract fun mapData(interactorData: ID): VD
}