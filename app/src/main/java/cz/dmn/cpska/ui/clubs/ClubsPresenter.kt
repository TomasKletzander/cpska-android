package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import javax.inject.Inject

@PerActivity
class ClubsPresenter @Inject constructor(private val clubsCache: MemoryCache<List<Club>>)
    : BaseMvpPresenter<ClubsMvp.View>(), ClubsMvp.Presenter {

    override fun attachView(view: ClubsMvp.View) {
        super.attachView(view)
        view.show(clubsCache.data)
    }
}