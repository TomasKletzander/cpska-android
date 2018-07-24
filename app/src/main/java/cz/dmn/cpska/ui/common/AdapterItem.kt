package cz.dmn.cpska.ui.common

interface AdapterItem {
    infix fun isSameAs(other: AdapterItem): Boolean
    infix fun isSameContentAs(other: AdapterItem): Boolean
}