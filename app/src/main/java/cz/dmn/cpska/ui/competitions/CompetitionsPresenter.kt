package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import javax.inject.Inject

@PerActivity
class CompetitionsPresenter @Inject constructor(
    private val competitionsCache: MemoryCache<List<Competition>>,
    override val state: EmptyPresenterState
) : BaseMvpPresenter<CompetitionsMvp.View, PresenterState<*>>(), CompetitionsMvp.Presenter {

    override fun attachView(view: CompetitionsMvp.View) {
        super.attachView(view)
        view.show(competitionsCache.data)
    }
}