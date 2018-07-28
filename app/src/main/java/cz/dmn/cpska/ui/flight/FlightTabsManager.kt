package cz.dmn.cpska.ui.flight

import android.view.View
import com.squareup.coordinators.Coordinator
import com.squareup.coordinators.CoordinatorProvider
import cz.dmn.cpska.R
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.flight.info.FlightInfoCoordinator
import javax.inject.Inject

@PerActivity
class FlightTabsManager @Inject constructor(
    private val flightInfoCoordinator: FlightInfoCoordinator
) : CoordinatorProvider {

    override fun provideCoordinator(view: View): Coordinator? = when (view.id) {
        R.id.flightInfo -> flightInfoCoordinator
        else -> null
    }


}