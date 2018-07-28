package cz.dmn.cpska.data.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CpsHtmlApi {
    @GET("/public/index3.php?lpg=sezlet")
    fun getFlights(@Query("strankovani")strankovani: Int): Observable<Response<ResponseBody>>

    @GET("/public/index3.php?lpg=piloti")
    fun getClubs(): Observable<Response<ResponseBody>>

    @GET("/public/index3.php?lpg=souteze")
    fun getCompetitions(): Observable<Response<ResponseBody>>

    @GET("/public/index3.php?lpg=souteze")
    fun getCompetitionDetails(@Query("soutezid") competitionId: Int): Observable<Response<ResponseBody>>

    @GET("/public/index3.php?lpg=zobraz_let")
    fun getFlightDetails(@Query("let_id") id: Int): Observable<Response<ResponseBody>>
}