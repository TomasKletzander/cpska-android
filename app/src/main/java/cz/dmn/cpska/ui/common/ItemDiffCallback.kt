package cz.dmn.cpska.ui.common

import android.support.v7.util.DiffUtil

class ItemDiffCallback(private val newItems: List<AdapterItem>, private val oldItems: List<AdapterItem>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItems[oldItemPosition] isSameAs newItems[newItemPosition]

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldItems[oldItemPosition] isSameContentAs newItems[newItemPosition]
}
