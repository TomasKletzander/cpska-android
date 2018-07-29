package cz.dmn.cpska.mvp

interface PagedDataPresenter<in ID, VD, V: PagedDataView<VD>> : MvpPresenter<V> {
    fun reset()
    fun loadNextPage()
}