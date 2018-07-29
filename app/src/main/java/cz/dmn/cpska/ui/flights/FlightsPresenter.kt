package cz.dmn.cpska.ui.flights

import android.content.res.Resources
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.interactors.FlightsInteractor
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BasePagedDataPresenter
import cz.dmn.cpska.mvp.BasePagedDataPresenterState
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.navigators.FlightNavigator
import cz.dmn.cpska.util.FavoriteClubsManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class FlightsPresenter @Inject constructor(
    private val interactor: FlightsInteractor,
    private val flightNavigator: FlightNavigator,
    @ByActivity private val res: Resources,
    private val favoriteClubsManager: FavoriteClubsManager,
    override val state: FlightsPresenterState
) : BasePagedDataPresenter<FlightData, Pair<FlightData, Boolean>, FlightsMvp.View>(interactor), FlightsMvp.Presenter {

    private var disposables = CompositeDisposable()
    private var _filterClub: Club? = null

    override var filterClub: Club?
        get() = _filterClub
        set(value) {
            _filterClub = value
            interactor.clubId = _filterClub?.id ?: 0
            reset()
            loadNextPage()
        }

    override fun attachView(view: FlightsMvp.View) {
        super.attachView(view)
        disposables.addAll(view.requestRefresh.subscribe {
            reset()
            loadNextPage()
        }, view.requestOpenFlight.subscribe {
            if (it.id <= 0) {
                this.view?.warning(res.getString(R.string.warningFlightProcessing))
            } else {
                flightNavigator.openFlight(it)
            }
        }, favoriteClubsManager.events.subscribe {
            refresh()
        })
    }

    override fun detachView() {
        disposables.clear()
        super.detachView()
    }

    override fun mapData(interactorData: FlightData) = Pair(interactorData, _filterClub == null && favoriteClubsManager.isFavorite(interactorData.club.name))
}