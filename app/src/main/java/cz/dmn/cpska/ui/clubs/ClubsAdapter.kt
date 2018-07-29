package cz.dmn.cpska.ui.clubs

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.databinding.ClubRowBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.ItemClickListener
import cz.dmn.cpska.ui.common.ItemDiffCallback
import cz.dmn.cpska.ui.common.ItemToggleListener
import javax.inject.Inject

@PerActivity
class ClubsAdapter @Inject constructor(
    private val inflater: LayoutInflater,
    private val clubClickListener: ItemClickListener<Club>,
    private val clubToggleListener: ItemToggleListener<Club>
) : RecyclerView.Adapter<ClubViewHolder>() {

    private val models = mutableListOf<ClubViewModel>()

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ClubViewHolder(ClubRowBinding.inflate(inflater, parent, false), clubClickListener, clubToggleListener)

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(models[position])
    }

    fun update(models: List<ClubViewModel>) {
        val diffResult = DiffUtil.calculateDiff(ItemDiffCallback(models, this.models), true)
        this.models.clear()
        this.models.addAll(models)
        diffResult.dispatchUpdatesTo(this)
    }
}