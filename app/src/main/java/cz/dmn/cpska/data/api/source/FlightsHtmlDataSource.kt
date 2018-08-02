package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.data.api.Plane
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.di.PerApplication
import cz.dmn.cpska.extensions.extractIdFromInnerHref
import io.reactivex.Observable
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

@PerApplication
class FlightsHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) : FlightsDataSource {

    companion object {
        val dateFormatter = DateTimeFormat.forPattern("dd.MM.yyyy")
    }

    override fun getPage(clubId: Int, pageIndex: Int): Observable<List<FlightData>> {
        return (if (clubId <= 0) api.getFlights(pageIndex * 100) else api.getClubFlights(pageIndex * 100, clubId))
                    .map {
                        val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                        val tables = doc.body().getElementsByClass("tblList")
                        (if (tables.size == 0) emptyList<Element>() else tables[0].getElementsByTag("tr")).filter {
                            it.hasClass("rowEven") || it.hasClass("rowOdd")
                        }.map {
                            val cells = it.getElementsByTag("td")
                            val id = cells[10].extractIdFromInnerHref()
                            val date = dateFormatter.parseLocalDate(cells[0].child(0).ownText())
                            val country = cells[2].ownText()
                            val pointsText = cells[3].ownText().split(" ")[0]
                            val points = if (pointsText.isEmpty()) 0 else pointsText.toInt()
                            val userName = cells[4].child(0).ownText()
                            val userId = cells[4].extractIdFromInnerHref()
                            val distanceText = cells[5].ownText().split(" ")[0]
                            var distance: Float
                            try {
                                distance = distanceText.toFloat()
                            } catch (e: Exception) {
                                distance = 0f
                            }
                            val speedText = cells[6].ownText().split(" ")[0]
                            var speed: Float
                            try {
                                speed = speedText.toFloat()
                            } catch (e: Exception) {
                                speed = 0f
                            }
                            val clubName = cells[7].ownText()
                            val planeName = cells[8].ownText()
                            FlightData(id, date, country, points, User(userId, userName), distance, speed, Club(0, clubName), Plane(planeName))
                        }
                    }
        }
}