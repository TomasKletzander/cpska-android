package cz.dmn.cpska.mvp

import cz.dmn.cpska.data.PagedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BasePagedDataPresenter<in D, V: PagedDataView<D>>(private val dataSource: PagedDataSource<D>) : PagedDataPresenter<D, V>, BaseMvpPresenter<V>() {
    private var pageIndex = 0
    private var loadingDisposable: Disposable? = null
    private var requestNextPageDisposable: Disposable? = null

    override fun attachView(view: V) {
        super.attachView(view)
        reset()
        loadNextPage()
        requestNextPageDisposable = view.requestNextPage.subscribe {
            if (loadingDisposable == null) {
                loadNextPage()
            }
        }
    }

    override fun reset() {
        view?.clear()
        pageIndex = 0
    }

    override fun loadNextPage() {
        if (loadingDisposable != null) throw RuntimeException("Can't load while already loading")
        view?.loading = true
        loadingDisposable = dataSource
                .getPage(pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadingDisposable = null
                    view?.apply {
                        loading = false
                        ++pageIndex
                        addPage(it)
                    }
                }, {
                    loadingDisposable = null
                    view?.apply {
                        loading = false
                        showError("Error: To be changed")
                    }
                })
    }
}