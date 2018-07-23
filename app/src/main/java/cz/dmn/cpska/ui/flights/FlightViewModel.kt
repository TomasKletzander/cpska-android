package cz.dmn.cpska.ui.flights

import android.graphics.drawable.Drawable
import cz.dmn.cpska.data.api.FlightData

class FlightViewModel(apiData: FlightData) {

    val date = apiData.date
    val country = apiData.country
    val points = apiData.points.toString()
    val name = apiData.user.name
    val distance = apiData.distance.toString() + " km"
    val speed = apiData.speed.toString() + " km/h"
    val club = apiData.club.name
    val glider = apiData.plane.name
    var background: Drawable? = null
}