package cz.dmn.cpska.data.api

data class Competition(
    val id: Int,
    val name: String,
    val categories: String,
    val interval: String,
    val flagUrl: String
)