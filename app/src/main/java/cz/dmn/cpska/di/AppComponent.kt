package cz.dmn.cpska.di

import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.data.DataModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class, DataModule::class])
interface AppComponent {
    fun inject(app: CpsApp)
}