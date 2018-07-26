package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition

data class CompetitionModel(val apiData: Competition) {
    val id = apiData.id
    val name = apiData.name
    val categories = apiData.categories
    val flagUrl = apiData.flagUrl
}