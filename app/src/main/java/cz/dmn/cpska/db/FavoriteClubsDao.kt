package cz.dmn.cpska.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface FavoriteClubsDao {

    @Query("SELECT * FROM FavoriteClubs")
    fun getAll(): List<FavoriteClubs>

    @Insert(onConflict = REPLACE)
    fun insert(favoriteClubs: FavoriteClubs)

    @Delete
    fun delete(favoriteClubs: FavoriteClubs)
}