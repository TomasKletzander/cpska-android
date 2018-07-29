package cz.dmn.cpska.ui.clubs

import android.support.v7.widget.RecyclerView
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.databinding.ClubRowBinding
import cz.dmn.cpska.ui.ItemClickListener
import cz.dmn.cpska.ui.common.AdapterItem
import cz.dmn.cpska.ui.common.ItemToggleListener
import javax.inject.Inject

class ClubViewHolder @Inject constructor(
    private val binding: ClubRowBinding,
    private val clickListener: ItemClickListener<Club>,
    private val toggleListener: ItemToggleListener<Club>
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.viewModel?.club?.let { clickListener.onItemClicked(it) }
        }
        binding.favorite.setOnClickListener {
            binding.viewModel?.club?.let { toggleListener.onToggleItem(it) }
        }
    }

    fun bind(viewModel: ClubViewModel) {
        binding.viewModel = viewModel
    }
}