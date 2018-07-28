package cz.dmn.cpska.data.api

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class User @ParcelConstructor constructor(val id: Int, val name: String) {

    val imageUrl: String
        get() = "https://www.cpska.cz/public/lib_load_image.php?image=../../data/pilot/mini/" + id + ".jpg"
}