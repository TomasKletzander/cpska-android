package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.PagedDataSource
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.Plane
import cz.dmn.cpska.data.api.User
import io.reactivex.Observable
import org.jsoup.Jsoup
import javax.inject.Inject

class FlightHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) : PagedDataSource<FlightData> {

    override fun getPage(pageIndex: Int): Observable<List<FlightData>> = api.getFlights(pageIndex * 100)
            .map {
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                val table = doc.body().getElementsByClass("tblList")[0]
                table.getElementsByTag("tr").filter {
                    it.hasClass("rowEven") || it.hasClass("rowOdd")
                }.map {
                    val cells = it.getElementsByTag("td")
                    val date = cells[0].ownText()
                    val country = cells[2].ownText()
                    val pointsText = cells[3].ownText().split(" ")[0]
                    val points = if (pointsText.isEmpty()) 0 else pointsText.toInt()
                    val userName = cells[4].child(0).ownText()
                    val distanceText = cells[5].ownText().split(" ")[0]
                    var distance : Float
                    try {
                        distance = distanceText.toFloat()
                    } catch(e: Exception) {
                        distance = 0f
                    }
                    val speedText = cells[6].ownText().split(" ")[0]
                    var speed: Float
                    try {
                        speed = speedText.toFloat()
                    } catch(e: Exception) {
                        speed = 0f
                    }
                    val clubName = cells[7].ownText()
                    val planeName = cells[8].ownText()
                    FlightData(date, country, points, User(0, userName), distance, speed, Club(clubName), Plane(planeName))
                }
            }
}