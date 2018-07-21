package cz.dmn.cpska.mvp

interface PagedDataPresenter<in D, V: PagedDataView<D>> : MvpPresenter<V> {
    fun reset()
    fun loadNextPage()
}