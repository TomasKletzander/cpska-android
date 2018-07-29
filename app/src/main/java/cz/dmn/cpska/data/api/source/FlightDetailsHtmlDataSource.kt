package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.di.PerApplication
import cz.dmn.cpska.extensions.extractIdFromInnerHref
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import javax.inject.Inject

@PerApplication
class FlightDetailsHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) : FlightDetailsDataSource {

    override fun getFlightDetails(id: Int) = api.getFlightDetails(id)
            .map {
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                val right = doc.getElementsByAttributeValue("id", "right")[0]
                val date = LocalDate.parse(right.child(0).text(), DateTimeFormat.forPattern("dd.MM.yyyy"))
                val panel = right.getElementsByClass("panel_lt")[2]
                val pointsText = panel.child(3).text().split(" ")[0]
                var points = 0
                try {
                    points = pointsText.toInt()
                } catch (e: Throwable) {}
                val distance = panel.child(1).text().split(" ")[0].toFloat()
                val speed = panel.child(2).text().split(" ")[0].toFloat()
                val panelUser = right.getElementsByClass("panel_pilot")[0]
                val user = User(panelUser.extractIdFromInnerHref(), panelUser.child(1).text())
                val clubName = panelUser.child(3).text()
                val panelPlane = right.getElementsByClass("panel_lt")[0]
                val planeName = panelPlane.child(1).child(0).text()
                val images = doc.getElementsByAttributeValue("id", "obsah")[0].child(1)
                val mapImageUrl = "https://www.cpska.cz/public/" + images.child(0).attr("src")
                val profileImageUrl = "https://www.cpska.cz/public/" + images.child(1).attr("src")
                FlightDetails(id, date, points, user, clubName, planeName, distance, speed, mapImageUrl, profileImageUrl)
            }
}