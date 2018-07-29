package cz.dmn.cpska.mvp

interface PagedDataPresenter<ID, in VD, in V: PagedDataView<VD>> : MvpPresenter<V, BasePagedDataPresenterState<ID, BasePagedDataPresenterState.StateHolder<ID>>> {
    fun reset()
    fun loadNextPage()
}