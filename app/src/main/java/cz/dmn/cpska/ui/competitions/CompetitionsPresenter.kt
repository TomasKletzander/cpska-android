package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import javax.inject.Inject

@PerActivity
class CompetitionsPresenter @Inject constructor(private val competitionsCache: MemoryCache<List<Competition>>)
    : BaseMvpPresenter<CompetitionsMvp.View>(), CompetitionsMvp.Presenter {

    override fun attachView(view: CompetitionsMvp.View) {
        super.attachView(view)
        view.show(competitionsCache.data)
    }
}