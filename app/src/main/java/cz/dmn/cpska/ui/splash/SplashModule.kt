package cz.dmn.cpska.ui.splash

import android.app.Activity
import cz.dmn.cpska.di.BaseActivityModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
interface SplashModule {

    @Binds
    fun bindActivity(activity: SplashActivity): Activity

    @Binds
    fun bindPresenter(presenter: SplashPresenter): SplashMvp.Presenter

    @Binds
    fun bindView(activity: SplashActivity): SplashMvp.View
}