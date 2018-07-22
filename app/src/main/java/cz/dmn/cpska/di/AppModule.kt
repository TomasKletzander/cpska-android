package cz.dmn.cpska.di

import android.content.Context
import android.content.res.Resources
import cz.dmn.cpska.CpsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: CpsApp) {

    @Provides
    fun provideApplication(): CpsApp = app

    @Provides
    @ByApplication
    fun provideAppContext(): Context = app

    @Provides
    @ByApplication
    fun provideResources(): Resources = app.resources
}