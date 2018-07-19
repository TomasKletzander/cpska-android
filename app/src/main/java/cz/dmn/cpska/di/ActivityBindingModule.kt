package cz.dmn.cpska.di

import cz.dmn.cpska.ui.home.HomeActivity
import cz.dmn.cpska.ui.home.HomeModule
import cz.dmn.cpska.ui.splash.SplashActivity
import cz.dmn.cpska.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashModule::class])
    fun contributeSplashActivityInjector(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    fun contributeHomeActivityInjector(): HomeActivity
}