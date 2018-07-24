package cz.dmn.cpska.ui.common

import com.brandongogetap.stickyheaders.exposed.StickyHeader

data class DateHeaderViewModel(val date: String) : StickyHeader, AdapterItem {

    override fun isSameAs(other: AdapterItem) = (other as? DateHeaderViewModel)?.date == date

    override fun isSameContentAs(other: AdapterItem) = (other as? DateHeaderViewModel)?.date == date
}