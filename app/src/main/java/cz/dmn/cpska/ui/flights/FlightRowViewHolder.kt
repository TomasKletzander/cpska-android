package cz.dmn.cpska.ui.flights

import android.net.Uri
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import cz.dmn.cpska.databinding.FlightsRowBinding
import cz.dmn.cpska.di.GlideApp

class FlightRowViewHolder(private val binding: FlightsRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: FlightViewModel) {
        binding.viewModel = model
        GlideApp.with(binding.profileImage)
                .load(Uri.parse("https://www.cpska.cz/public/lib_load_image.php?image=../../data/pilot/mini/" + model.userId + ".jpg"))
                .apply(RequestOptions.circleCropTransform())
                .into(binding.profileImage)
    }
}