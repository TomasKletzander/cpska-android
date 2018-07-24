package cz.dmn.cpska.data.api.source

import android.util.Log
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.Plane
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Observable
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

@PerApplication
class FlightHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) {

    companion object {
        val dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy")

        fun extractIdFromInnerHref(td: Element): Int {
            val links = td.getElementsByTag("a")
            if (links.size == 0) return 0
            val href = links[0].attr("href")
            val parts = href.split("=")
            return parts.lastOrNull()?.toInt() ?: 0
        }
    }

    fun getPage(pageIndex: Int): Observable<List<FlightData>> = api.getFlights(pageIndex * 100)
            .map {
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                val table = doc.body().getElementsByClass("tblList")[0]
                table.getElementsByTag("tr").filter {
                    it.hasClass("rowEven") || it.hasClass("rowOdd")
                }.map {
                    val cells = it.getElementsByTag("td")
                    val id = extractIdFromInnerHref(cells[10])
                    val date = dateFormatter.parseLocalDate(cells[0].child(0).ownText())
                    val country = cells[2].ownText()
                    val pointsText = cells[3].ownText().split(" ")[0]
                    val points = if (pointsText.isEmpty()) 0 else pointsText.toInt()
                    val userName = cells[4].child(0).ownText()
                    val userId = extractIdFromInnerHref(cells[4])
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
                    FlightData(id, date, country, points, User(userId, userName), distance, speed, Club(0, clubName), Plane(planeName))
                }
            }
}