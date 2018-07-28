package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.interactors.FlightsInteractor
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BasePagedDataPresenter
import cz.dmn.cpska.navigators.FlightNavigator
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class FlightsPresenter @Inject constructor(
    interactor: FlightsInteractor,
    private val flightNavigator: FlightNavigator
) : BasePagedDataPresenter<FlightData, FlightsMvp.View>(interactor), FlightsMvp.Presenter {

    var disposables = CompositeDisposable()

    override fun attachView(view: FlightsMvp.View) {
        super.attachView(view)
        disposables.add(view.requestRefresh.subscribe {
            reset()
            loadNextPage()
        })
        disposables.add(view.requestOpenFlight.subscribe {
            flightNavigator.openFlight(it)
        })
    }

    override fun detachView() {
        disposables.clear()
        super.detachView()
    }
}