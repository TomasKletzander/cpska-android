package cz.dmn.cpska.ui.competitions

import android.view.View
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.databinding.CoordCompetitionsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerActivity
class CompetitionsCoordinator @Inject constructor(
    private val adapter: CompetitionsAdapter,
    private val binding: CoordCompetitionsBinding
) : BaseMvpCoordinator<CompetitionsMvp.View, CompetitionsMvp.Presenter>(), CompetitionsMvp.View {

    private val refreshPublishSubject = PublishSubject.create<Any>()

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
    }

    override var loading: Boolean
        get() = false
        set(value) {}

    override val refreshSubject: Subject<Any>
        get() = refreshPublishSubject

    override fun show(competitions: List<Competition>) {
        adapter.replace(competitions.map { CompetitionModel(it) })
    }

    override fun error(message: String) {
    }
}