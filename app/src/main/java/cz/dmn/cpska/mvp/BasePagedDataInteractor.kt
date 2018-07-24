package cz.dmn.cpska.mvp

import cz.dmn.cpska.data.interactors.BaseInteractor

abstract class BasePagedDataInteractor<D> : BaseInteractor<List<D>>() {
    var page: Int = 0
}