package cz.dmn.cpska.ui.common

object LoaderViewModel : AdapterItem {

    override fun isSameAs(other: AdapterItem) = other is LoaderViewModel

    override fun isSameContentAs(other: AdapterItem) = other is LoaderViewModel
}