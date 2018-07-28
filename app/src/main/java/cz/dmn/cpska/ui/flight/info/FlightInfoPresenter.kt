package cz.dmn.cpska.ui.flight.info

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import javax.inject.Inject

@PerActivity
class FlightInfoPresenter @Inject constructor() : BaseMvpPresenter<FlightInfoMvp.View>(), FlightInfoMvp.Presenter {

    private var pendingDetails: FlightDetails? = null

    override fun attachView(view: FlightInfoMvp.View) {
        super.attachView(view)
        pendingDetails?.let { view.show(it) }
    }

    override fun show(flightDetails: FlightDetails) {
        pendingDetails = flightDetails
        view?.show(flightDetails)
    }
}