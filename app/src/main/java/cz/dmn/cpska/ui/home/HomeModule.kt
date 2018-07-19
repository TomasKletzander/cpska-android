package cz.dmn.cpska.ui.home

import android.app.Activity
import cz.dmn.cpska.di.BaseActivityModule
import dagger.Binds
import dagger.Module

@Module(includes = [BaseActivityModule::class])
interface HomeModule {
    @Binds
    fun bindActivity(activity: HomeActivity): Activity
}