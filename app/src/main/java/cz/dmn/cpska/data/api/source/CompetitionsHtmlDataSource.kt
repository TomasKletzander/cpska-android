package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.extensions.extractIdFromInnerHref
import cz.dmn.cpska.extensions.trimBrackets
import io.reactivex.Observable
import org.jsoup.Jsoup
import javax.inject.Inject

class CompetitionsHtmlDataSource @Inject constructor(private val api: CpsHtmlApi) : CompetitionsDataSource {

    override fun getCompetitions (): Observable<List<Competition>> = api.getCompetitions()
            .map {
                val competitions = mutableListOf<Competition>()
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                doc.getElementsByClass("souteze_graf")[0].getElementsByTag("tr").filter {
                    it.hasClass("rowOdd") || it.hasClass("rowEven")
                }.forEach {
                    val cells = it.children()
                    val id = cells[1].extractIdFromInnerHref()
                    val name = cells[1].child(0).text().trim()
                    val categories = cells[1].ownText().trim().trimBrackets()
                    val interval = cells[0].getElementsByClass("pole")[1].text().trim()
                    val flagUrl = "https://www.cpska.cz/public/" + cells[1].getElementsByTag("img")[0].attr("src")
                    competitions.add(Competition(id, name, categories, interval, flagUrl))
                }
                competitions
            }
}