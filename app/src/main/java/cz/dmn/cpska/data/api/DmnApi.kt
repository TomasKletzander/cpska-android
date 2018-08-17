package cz.dmn.cpska.data.api

import cz.dmn.cpska.data.api.cfg.Configuration
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface DmnApi {

    @GET("config")
    fun getConfiguration(): Observable<Configuration>

    @GET
    fun getFoggles(@Url url: String): Observable<Map<String, Boolean>>
}