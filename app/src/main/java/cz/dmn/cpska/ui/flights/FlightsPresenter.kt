package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.Plane
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpPresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScope
class FlightsPresenter @Inject constructor() : BaseMvpPresenter<FlightsMvp.View>(), FlightsMvp.Presenter {

    private var disposables = CompositeDisposable()

    override fun load() {
        view?.apply {
            //  TODO: Replace with real API call
            val flights = listOf(
                    FlightData("19.7.2018", "cz", 1200, User(1, "Jan Pubec"), 639.2f, 84.42f, Club("Plzen-Bory"), Plane("Std. Cirrus")),
                    FlightData("19.7.2018", "cz", 1012, User(2, "David Popovsky"), 550.7f, 80.25f, Club("Rakovnik"), Plane("ASW-24(A)"))
            )
            disposables.add(Observable.just(flights).delay(2000, TimeUnit.MILLISECONDS).subscribe {
                show(it)
            })
        }
    }

    override fun attachView(view: FlightsMvp.View) {
        super.attachView(view)
        load()
    }

    override fun detachView() {
        disposables.clear()
        super.detachView()
    }
}