package cz.dmn.cpska.db

import android.arch.persistence.room.Room
import android.content.Context
import cz.dmn.cpska.di.ByApplication
import dagger.Module
import dagger.Provides

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideCpskaDatabase(@ByApplication context: Context): CpsDatabase = Room.databaseBuilder(context, CpsDatabase::class.java, "cpska.db").build()

        @Provides
        @JvmStatic
        fun provideFavoriteClubsDao(database: CpsDatabase): FavoriteClubsDao = database.favoriteClubsDao()
    }
}