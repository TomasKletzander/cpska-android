package cz.dmn.cpska.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class FavoriteClubs(
    @PrimaryKey(autoGenerate = false) var clubId: Int?
) {
    constructor(): this(null)
}