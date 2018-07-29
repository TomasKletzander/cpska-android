package cz.dmn.cpska.navigators

import android.support.v7.app.AppCompatActivity
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.flight.FlightActivity
import cz.dmn.cpska.ui.flight.FlightConstants
import javax.inject.Inject

@PerActivity
class FlightNavigator @Inject constructor(activity: AppCompatActivity) : BaseNavigator(activity) {

    fun openFlight(flight: FlightData) {
        startActivity(FlightActivity::class.java, Pair(FlightConstants.EXTRA_FLIGHT, flight))
    }
}