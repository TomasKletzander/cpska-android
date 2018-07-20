package cz.dmn.cpska.data.api

data class FlightData(
    val date: String,
    val country: String,
    val points: Int,
    val user: User,
    val distance: Float,
    val speed: Float,
    val club: Club,
    val plane: Plane
)