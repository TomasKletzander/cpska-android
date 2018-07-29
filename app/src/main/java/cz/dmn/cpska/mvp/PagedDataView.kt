package cz.dmn.cpska.mvp

import io.reactivex.subjects.Subject

interface PagedDataView<in D> : MvpView {
    fun clear()
    fun show(data: List<D>)
    fun error(message: String)
    var loading: Boolean
    val requestNextPage: Subject<Any>
}