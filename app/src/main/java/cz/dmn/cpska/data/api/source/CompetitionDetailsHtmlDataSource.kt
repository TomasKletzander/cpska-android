package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.CompetitionClass
import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.data.api.CpsHtmlApi
import cz.dmn.cpska.di.PerApplication
import org.jsoup.Jsoup
import javax.inject.Inject

@PerApplication
class CompetitionDetailsHtmlDataSource @Inject constructor(private val api: CpsHtmlApi)
    : CompetitionDetailsDataSource {

    override fun getCompetitionDetails(id: Int) = api.getCompetitionDetails(id)
            .map {
                val doc = Jsoup.parse(it.body()?.byteStream(), "windows-1250", "")
                val obsah = doc.getElementsByAttributeValue("id", "obsah")[0].children()

                //  Find last tblList child index
                var startIndex = 0
                for (idx in obsah.size .. 0) {
                    if (obsah[idx].hasClass("tblList")) {
                        startIndex = idx
                        break
                    }
                }

                //  Find all classes below last table
                val classes = mutableListOf<CompetitionClass>()
                obsah.filterIndexed { index, element ->
                    index > startIndex && element.tagName() == "h3"
                }.forEach {
                }

                CompetitionDetails(id, "", classes)
            }
}