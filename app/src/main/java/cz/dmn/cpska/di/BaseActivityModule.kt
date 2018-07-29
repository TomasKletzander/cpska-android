package cz.dmn.cpska.di

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.MenuInflater
import cz.dmn.cpska.data.api.source.FlightsDataSource
import cz.dmn.cpska.data.api.source.FlightsHtmlDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class BaseActivityModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @ByActivity
        fun provideResources(activity: Activity): Resources = activity.resources

        @Provides
        @JvmStatic
        fun provideLayoutInflater(activity: Activity): LayoutInflater = LayoutInflater.from(activity)

        @Provides
        @JvmStatic
        @ByActivity
        fun provideMenuInflater(activity: Activity): MenuInflater = activity.menuInflater
    }

    @Binds
    @ByActivity
    abstract fun bindActivityContext(activity: Activity): Context
}