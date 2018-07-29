package cz.dmn.cpska.ui.flight

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.FlightDetailsInteractor
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import cz.dmn.cpska.ui.flight.info.FlightInfoPresenter
import javax.inject.Inject

@PerActivity
class FlightPresenter @Inject constructor(
    private val interactor: FlightDetailsInteractor,
    private val flight: FlightData,
    private val flightInfoPresenter: FlightInfoPresenter,
    override val state: FlightPresenterState
) : BaseMvpPresenter<FlightMvp.View, PresenterState<*>>(), FlightMvp.Presenter {

    override fun load() {
        interactor.flightId = flight.id
        interactor.execute(object : BaseInteractorSubscriber<FlightDetails>() {

            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(t: FlightDetails) {
                state.flightDetails = t
                view?.apply {
                    loading = false
                    show(t)
                }
                flightInfoPresenter.show(t)
            }

            override fun onError(e: Throwable) {
                view?.apply {
                    loading = false
                    error(e.localizedMessage)
                }
            }
        })
    }

    override fun attachView(view: FlightMvp.View) {
        super.attachView(view)
        state.flightDetails.let {
            if (it == null) {
                load()
            } else {
                view.show(it)
                flightInfoPresenter.show(it)
            }
        }
    }

    override fun detachView() {
        interactor.unsubscribe()
        super.detachView()
    }
}