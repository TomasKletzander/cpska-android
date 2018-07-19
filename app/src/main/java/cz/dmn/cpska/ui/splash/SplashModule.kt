package cz.dmn.cpska.ui.splash

import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {
    @Binds
    abstract fun bindPresenter(presenter: SplashPresenter): SplashMvp.Presenter

    @Binds
    abstract fun bindView(activity: SplashActivity): SplashMvp.View
}