package cz.dmn.cpska.di

import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.data.DataModule
import cz.dmn.cpska.data.api.source.DataSourceModule
import cz.dmn.cpska.db.DatabaseModule
import dagger.Component
import dagger.android.AndroidInjectionModule

@PerApplication
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    DataModule::class,
    DataSourceModule::class,
    DatabaseModule::class
])
interface AppComponent {
    fun inject(app: CpsApp)
}