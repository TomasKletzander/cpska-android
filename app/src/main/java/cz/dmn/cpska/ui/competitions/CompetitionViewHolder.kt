package cz.dmn.cpska.ui.competitions

import android.support.v7.widget.RecyclerView
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.CompetitionsRowBinding
import cz.dmn.cpska.ui.ItemClickListener

class CompetitionViewHolder(
    private val binding: CompetitionsRowBinding,
    private val clickListener: ItemClickListener<Competition>
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            binding.viewModel?.apiData?.let { clickListener.onItemClicked(it) }
        }
    }

    fun bind(viewModel: CompetitionModel) {
        binding.viewModel = viewModel
    }
}