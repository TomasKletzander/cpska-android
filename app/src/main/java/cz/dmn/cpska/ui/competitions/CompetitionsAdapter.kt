package cz.dmn.cpska.ui.competitions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.CompetitionsRowBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.ItemClickListener
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerActivity
class CompetitionsAdapter @Inject constructor(private val inflater: LayoutInflater)
    : RecyclerView.Adapter<CompetitionViewHolder>() {

    private val models = mutableListOf<CompetitionModel>()

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = CompetitionViewHolder(CompetitionsRowBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.bind(models[position])
    }

    fun replace(newModels: List<CompetitionModel>) {
        models.clear()
        models.addAll(newModels)
    }
}