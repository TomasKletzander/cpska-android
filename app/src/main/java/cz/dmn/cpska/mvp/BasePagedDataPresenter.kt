package cz.dmn.cpska.mvp

import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import io.reactivex.disposables.Disposable

abstract class BasePagedDataPresenter<ID, VD, V: PagedDataView<VD>>(
    private val interactor: BasePagedDataInteractor<ID>
) : PagedDataPresenter<ID, VD, V>, BaseMvpPresenter<V, BasePagedDataPresenterState<ID, BasePagedDataPresenterState.StateHolder<ID>>>() {

    private var loading = false
    private var requestNextPageDisposable: Disposable? = null

    override fun attachView(view: V) {
        super.attachView(view)
        if (state.data.isEmpty() && state.page == 0) {
            reset()
            loadNextPage()
        } else {
            refresh()
        }
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
        state.data.clear()
        state.page = 0
        interactor.unsubscribe()
        loading = false
    }

    override fun loadNextPage() {
        if (loading) return
        loading = true
        interactor.page = state.page
        interactor.execute(object : BaseInteractorSubscriber<List<ID>>() {
            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(t: List<ID>) {
                state.data.addAll(t)
                view?.apply {
                    show(state.data.map { mapData(it) })
                    loading = false
                }
                ++state.page
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
        view?.show(state.data.map { mapData(it) })
    }

    abstract fun mapData(interactorData: ID): VD
}