package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import cz.dmn.cpska.navigators.ClubNavigator
import cz.dmn.cpska.util.FavoriteClubsManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class ClubsPresenter @Inject constructor(
    private val clubsCache: MemoryCache<List<Club>>,
    private val clubNavigator: ClubNavigator,
    private val favoriteClubsManager: FavoriteClubsManager,
    override val state: EmptyPresenterState
) : BaseMvpPresenter<ClubsMvp.View, PresenterState<*>>(), ClubsMvp.Presenter {

    private val disposables = CompositeDisposable()

    override fun attachView(view: ClubsMvp.View) {
        super.attachView(view)
        disposables.addAll(view.requestOpenClub.subscribe {
            clubNavigator.navigateToClub(it)
        }, view.toggleFavoriteClub.subscribe {
            toggleFavoriteClub(it)
        }, favoriteClubsManager.events.subscribe {
            refreshView()
        })
        refreshView()
    }

    private fun refreshView() {
        view?.show(clubsCache.data.map { Pair(it, favoriteClubsManager.isFavorite(it.id)) })
    }

    private fun toggleFavoriteClub(club: Club) {
        if (favoriteClubsManager.isFavorite(club.id)) {
            favoriteClubsManager.removeFavorite(club.id)
        } else {
            favoriteClubsManager.addFavorite(club.id)
        }
    }
}