package cz.dmn.cpska.di

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class BaseActivityModule {

    @Module
    companion object {

        @Provides
        fun provideResources(activity: Activity): Resources = activity.resources

    }

    @Provides
    fun bindActivityContext(activity: Activity): Context = activity

    @Provides
    fun provideLayoutInflater(activity: Activity): LayoutInflater = LayoutInflater.from(activity)
}