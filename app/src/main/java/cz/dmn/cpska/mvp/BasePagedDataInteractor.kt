package cz.dmn.cpska.mvp

import cz.dmn.cpska.data.interactors.BaseInteractor

abstract class BasePagedDataInteractor<D> : BaseInteractor<D>() {
    var page: Int = 0
}