package cz.dmn.cpska.ui.clubs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.dmn.cpska.databinding.ClubRowBinding
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class ClubsAdapter @Inject constructor(private val inflater: LayoutInflater) : RecyclerView.Adapter<ClubViewHolder>() {

    private val models = mutableListOf<ClubViewModel>()

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClubViewHolder(ClubRowBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(models[position])
    }

    fun update(models: List<ClubViewModel>) {
        this.models.clear()
        this.models.addAll(models)
        notifyDataSetChanged()
    }
}