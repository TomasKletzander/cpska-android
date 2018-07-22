package cz.dmn.cpska.data.api

import org.joda.time.LocalDate

data class FlightData(
    val date: LocalDate,
    val country: String,
    val points: Int,
    val user: User,
    val distance: Float,
    val speed: Float,
    val club: Club,
    val plane: Plane
)