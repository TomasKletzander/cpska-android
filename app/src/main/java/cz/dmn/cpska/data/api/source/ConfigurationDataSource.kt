package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.cfg.Configuration
import io.reactivex.Observable

interface ConfigurationDataSource {
    fun getConfiguration(): Observable<Configuration>
}