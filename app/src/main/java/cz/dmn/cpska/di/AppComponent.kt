package cz.dmn.cpska.di

import cz.dmn.cpska.CpsApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent {

    fun inject(app: CpsApp)
}