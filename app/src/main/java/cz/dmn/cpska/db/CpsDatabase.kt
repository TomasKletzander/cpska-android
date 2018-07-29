package cz.dmn.cpska.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [FavoriteClubs::class], version = 1)
abstract class CpsDatabase : RoomDatabase() {

    abstract fun favoriteClubsDao(): FavoriteClubsDao
}