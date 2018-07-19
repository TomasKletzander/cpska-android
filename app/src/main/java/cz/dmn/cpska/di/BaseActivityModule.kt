package cz.dmn.cpska.di

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class BaseActivityModule {

    @Module
    companion object {

        @Provides
        fun provideResources(activity: Activity): Resources = activity.resources
    }

    @Binds
    abstract fun bindActivityContext(activity: Activity): Context
}