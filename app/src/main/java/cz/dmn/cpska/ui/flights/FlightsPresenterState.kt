package cz.dmn.cpska.ui.flights

import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BasePagedDataPresenterState
import org.parceler.Parcel
import javax.inject.Inject

@PerActivity
class FlightsPresenterState @Inject constructor()
    : BasePagedDataPresenterState<FlightData, BasePagedDataPresenterState.StateHolder<FlightData>>() {

    @Parcel(Parcel.Serialization.BEAN)
    class StateHolder : BasePagedDataPresenterState.StateHolder<FlightData>()

    override fun newStateHolder() = StateHolder()
}