package cz.dmn.cpska.data.api

import cz.dmn.cpska.data.api.cfg.Configuration
import io.reactivex.Observable
import retrofit2.http.GET

interface DmnApi {

    @GET("config")
    fun getConfiguration(): Observable<Configuration>
}