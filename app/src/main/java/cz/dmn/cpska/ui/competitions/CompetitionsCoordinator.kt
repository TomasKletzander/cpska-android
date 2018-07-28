package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.CoordCompetitionsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import javax.inject.Inject

@PerActivity
class CompetitionsCoordinator @Inject constructor(
    private val adapter: CompetitionsAdapter
) : BaseMvpCoordinator<CompetitionsMvp.View, CompetitionsMvp.Presenter, CoordCompetitionsBinding>(), CompetitionsMvp.View {

    override fun onAttach() {
        binding.adapter = adapter
    }

    override fun show(competitions: List<Competition>) {
        adapter.replace(competitions.map { CompetitionModel(it) })
    }
}