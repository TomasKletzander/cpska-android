package cz.dmn.cpska.ui.splash

import android.support.v7.app.AppCompatActivity
import cz.dmn.cpska.di.BaseActivityModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
interface SplashModule {

    @Binds
    fun bindActivity(activity: SplashActivity): AppCompatActivity

    @Binds
    fun bindPresenter(presenter: SplashPresenter): SplashMvp.Presenter

    @Binds
    fun bindView(activity: SplashActivity): SplashMvp.View
}