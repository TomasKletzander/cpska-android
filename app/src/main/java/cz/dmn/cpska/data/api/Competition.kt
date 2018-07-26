package cz.dmn.cpska.data.api

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Competition @ParcelConstructor constructor(
    val id: Int,
    val name: String,
    val categories: String,
    val interval: String,
    val flagUrl: String
)