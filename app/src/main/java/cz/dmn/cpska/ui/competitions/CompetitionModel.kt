package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition

data class CompetitionModel(val id: Int, val name: String, val categories: String, val flagUrl: String) {
    constructor(apiData: Competition) : this(
            apiData.id,
            apiData.name,
            apiData.categories,
            apiData.flagUrl)
}