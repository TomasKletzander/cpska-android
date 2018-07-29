package cz.dmn.cpska.di

import android.content.Context
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuInflater
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
        fun provideResources(activity: AppCompatActivity): Resources = activity.resources

        @Provides
        @JvmStatic
        fun provideLayoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

        @Provides
        @JvmStatic
        @ByActivity
        fun provideMenuInflater(activity: AppCompatActivity): MenuInflater = activity.menuInflater
    }

    @Binds
    @ByActivity
    abstract fun bindActivityContext(activity: AppCompatActivity): Context
}