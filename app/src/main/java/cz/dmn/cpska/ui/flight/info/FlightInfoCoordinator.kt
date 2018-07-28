package cz.dmn.cpska.ui.flight.info

import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.view.View
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.databinding.CoordFlightInfoBinding
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import javax.inject.Inject

@PerActivity
class FlightInfoCoordinator @Inject constructor(
    private val viewModel: FlightInfoViewModel,
    @ByActivity private val res: Resources
) : BaseMvpCoordinator<FlightInfoMvp.View, FlightInfoMvp.Presenter, CoordFlightInfoBinding>(), FlightInfoMvp.View {

    override fun onAttach() {
        binding.viewModel = viewModel
    }

    override fun show(flightDetails: FlightDetails) {
        viewModel.apply {
            pilotName.set(flightDetails.user.name)
            pilotImageUrl.set(flightDetails.user.imageUrl)
            clubName.set(flightDetails.clubName)
            planeName.set(flightDetails.planeName)
            distance.set(res.getString(R.string.distanceFormat, flightDetails.distance))
            speed.set(res.getString(R.string.speedFormat, flightDetails.speed))
            points.set(res.getString(R.string.pointsFormat, flightDetails.points))
            mapImageUrl.set(flightDetails.mapImageUrl)
            profileImageUrl.set(flightDetails.profileImageUrl)
        }
    }
}