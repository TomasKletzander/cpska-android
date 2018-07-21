package cz.dmn.cpska.data

import io.reactivex.Observable

interface PagedDataSource<D> {
    fun getPage(pageIndex: Int): Observable<List<D>>
}