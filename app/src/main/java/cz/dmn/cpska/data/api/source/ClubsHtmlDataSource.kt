package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Observable
import org.jsoup.Jsoup
import javax.inject.Inject

@PerApplication
class ClubsHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) {

    fun getClubs(): Observable<List<Club>> = api.getClubs()
            .map {
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                val select = doc.getElementsByAttributeValue("name", "klub")[0]
                select.getElementsByTag("option").map {
                    val id = it.`val`().toInt()
                    val name = it.ownText().trim()
                    Club(id, name)
                }
            }
}