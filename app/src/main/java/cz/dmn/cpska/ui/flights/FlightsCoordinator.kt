package cz.dmn.cpska.ui.flights

import android.view.View
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.CoordFlightsBinding
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import javax.inject.Inject

@ActivityScope
class FlightsCoordinator @Inject constructor(private val adapter: FlightsAdapter, private val binding: CoordFlightsBinding)
    : BaseMvpCoordinator<FlightsMvp.View, FlightsMvp.Presenter>(), FlightsMvp.View {

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
    }

    override fun show(flights: List<FlightData>) {
        adapter.load(flights)
    }
}