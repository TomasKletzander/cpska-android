package cz.dmn.cpska.ui.clubs

import android.support.v7.widget.RecyclerView
import cz.dmn.cpska.databinding.ClubRowBinding
import javax.inject.Inject

class ClubViewHolder @Inject constructor(private val binding: ClubRowBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: ClubViewModel) {
        binding.viewModel = viewModel
    }
}