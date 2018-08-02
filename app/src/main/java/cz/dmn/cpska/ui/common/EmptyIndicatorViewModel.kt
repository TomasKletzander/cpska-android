package cz.dmn.cpska.ui.common

class EmptyIndicatorViewModel(val message: String) : AdapterItem {

    override fun isSameAs(other: AdapterItem) = other is EmptyIndicatorViewModel

    override fun isSameContentAs(other: AdapterItem) = (other as? EmptyIndicatorViewModel)?.message == message
}