package cz.dmn.cpska.data.api

import org.joda.time.LocalDate

data class FlightDetails(
    val id: Int,
    val date: LocalDate,
    val points: Int,
    val user: User,
    val clubName: String,
    val planeName: String,
    val distance: Float,
    val speed: Float,
    val mapImageUrl: String,
    val profileImageUrl: String
)