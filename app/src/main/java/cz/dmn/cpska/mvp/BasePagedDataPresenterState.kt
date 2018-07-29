package cz.dmn.cpska.mvp

abstract class BasePagedDataPresenterState<ID, SH: BasePagedDataPresenterState.StateHolder<ID>> : PresenterState<SH>() {

    abstract class StateHolder<ID> {
        var page = 0
        var data = mutableListOf<ID>()
    }

    var page: Int
        get() = stateHolder.page
        set(value) { stateHolder.page = value }

    val data: MutableList<ID>
        get() = stateHolder.data
}