package cz.dmn.cpska.di

import cz.dmn.cpska.ui.competition.CompetitionActivity
import cz.dmn.cpska.ui.competition.CompetitionModule
import cz.dmn.cpska.ui.flight.FlightActivity
import cz.dmn.cpska.ui.flight.FlightModule
import cz.dmn.cpska.ui.home.HomeActivity
import cz.dmn.cpska.ui.home.HomeModule
import cz.dmn.cpska.ui.splash.SplashActivity
import cz.dmn.cpska.ui.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    fun contributeSplashActivityInjector(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeModule::class])
    fun contributeHomeActivityInjector(): HomeActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [CompetitionModule::class])
    fun contributeCompetitionActivityInjector(): CompetitionActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [FlightModule::class])
    fun contributeFlightActivityInjector(): FlightActivity
}