package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.interactors.LoadFlightsInteractor
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BasePagedDataPresenter
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerActivity
class FlightsPresenter @Inject constructor(interactor: LoadFlightsInteractor)
    : BasePagedDataPresenter<FlightData, FlightsMvp.View>(interactor), FlightsMvp.Presenter {

    var requestRefreshDisposable: Disposable? = null

    override fun attachView(view: FlightsMvp.View) {
        super.attachView(view)
        requestRefreshDisposable = view.requestRefresh.subscribe {
            reset()
            loadNextPage()
        }
    }

    override fun detachView() {
        requestRefreshDisposable?.dispose()
        requestRefreshDisposable = null
        super.detachView()
    }
}