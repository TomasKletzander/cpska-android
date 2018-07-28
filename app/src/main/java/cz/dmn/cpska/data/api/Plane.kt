package cz.dmn.cpska.data.api

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Plane @ParcelConstructor constructor(val name: String)