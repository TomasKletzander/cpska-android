package cz.dmn.cpska.data.api

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CpsHtmlApi {
    @GET("/public/index3.php?lpg=sezlet")
    fun getFlights(@Query("strankovani")strankovani: Int): Observable<Response<ResponseBody>>
}