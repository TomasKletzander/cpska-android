package cz.dmn.cpska.ui.competitions

import android.support.v7.widget.RecyclerView
import cz.dmn.cpska.databinding.CompetitionsRowBinding

class CompetitionViewHolder(private val binding: CompetitionsRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: CompetitionModel) {
        binding.viewModel = viewModel
    }
}