package cz.dmn.cpska.ui.competitions

import android.view.View
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.CoordCompetitionsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import cz.dmn.cpska.navigators.CompetitionNavigator
import javax.inject.Inject

@PerActivity
class CompetitionsCoordinator @Inject constructor(
    private val adapter: CompetitionsAdapter,
    private val binding: CoordCompetitionsBinding,
    private val competitionNavigator: CompetitionNavigator
) : BaseMvpCoordinator<CompetitionsMvp.View, CompetitionsMvp.Presenter>(), CompetitionsMvp.View {

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
        adapter.competitionClickSubject.subscribe {
            competitionNavigator.openCompetition(it)
        }
    }

    override fun show(competitions: List<Competition>) {
        adapter.replace(competitions.map { CompetitionModel(it) })
    }
}