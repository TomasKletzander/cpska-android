package cz.dmn.cpska.data.api

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Club @ParcelConstructor constructor(val id: Int, val name: String) {
    override fun toString() = name
}