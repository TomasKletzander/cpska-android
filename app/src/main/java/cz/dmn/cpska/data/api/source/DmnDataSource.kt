package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.DmnApi
import cz.dmn.cpska.di.PerApplication
import javax.inject.Inject

@PerApplication
class DmnDataSource @Inject constructor(private val api: DmnApi) : ConfigurationDataSource {

    override fun getConfiguration() = api.getConfiguration()
}