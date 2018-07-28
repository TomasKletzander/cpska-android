package cz.dmn.cpska.data.api

import org.joda.time.LocalDate
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class FlightData @ParcelConstructor constructor(
    val id: Int,
    val date: LocalDate,
    val country: String,
    val points: Int,
    val user: User,
    val distance: Float,
    val speed: Float,
    val club: Club,
    val plane: Plane
)