package cz.dmn.cpska.di

import cz.dmn.cpska.CpsApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: CpsApp) {

    @Provides
    @Singleton
    fun provideApplication(): CpsApp = app
}