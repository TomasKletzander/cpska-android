package cz.dmn.cpska.ui.flight

import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.PresenterState
import org.parceler.Parcel
import javax.inject.Inject

@PerActivity
class FlightPresenterState @Inject constructor() : PresenterState<FlightPresenterState.StateHolder>() {

    @Parcel(Parcel.Serialization.BEAN)
    class StateHolder {
        @JvmField var flightDetails: FlightDetails? = null
    }

    override fun newStateHolder() = StateHolder()

    var flightDetails: FlightDetails?
        get() = stateHolder.flightDetails
        set(value) { stateHolder.flightDetails = value }
}