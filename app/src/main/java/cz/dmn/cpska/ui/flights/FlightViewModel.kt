package cz.dmn.cpska.ui.flights

import android.content.res.Resources
import android.graphics.drawable.Drawable
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.ui.common.AdapterItem

class FlightViewModel(val apiData: FlightData, res: Resources) : AdapterItem {

    val id = apiData.id
    val date = apiData.date
    val country = apiData.country
    val points = apiData.points.toString() + res.getString(R.string.pointsAbbrev)
    val userId = apiData.user.id
    val name = apiData.user.name
    val distance = apiData.distance.toString() + " km"
    val speed = apiData.speed.toString() + " km/h"
    val club = apiData.club.name
    val glider = apiData.plane.name
    var background: Drawable? = null

    override fun isSameAs(other: AdapterItem) = (other as? FlightViewModel)?.id == id

    override fun isSameContentAs(other: AdapterItem) = (other as? FlightViewModel)?.let {
        it.id == id && it.date == date && it.country == country && it.points == points && it.name == name &&
                it.distance == distance && it.speed == speed && it.club == club && it.glider == glider &&
                it.background == background
    } ?: false
}