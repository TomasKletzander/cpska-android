package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.DmnApi
import cz.dmn.cpska.data.api.cfg.Configuration
import cz.dmn.cpska.di.PerApplication
import dagger.Lazy
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class DmnDataSource @Inject constructor(private val cfgLazy: Lazy<Configuration>, private val api: DmnApi) : ConfigurationDataSource, FogglesDataSource {

    override fun getConfiguration() = api.getConfiguration()
    override fun getFoggles() = api.getFoggles(cfgLazy.get().foggles.url)
}