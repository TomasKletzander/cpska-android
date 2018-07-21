package cz.dmn.cpska.mvp

import io.reactivex.subjects.Subject

interface PagedDataView<in D> : MvpView {
    fun clear()
    fun addPage(pageData: List<D>)
    fun showError(message: String)
    var loading: Boolean
    val requestNextPage: Subject<Any>
}