package cz.dmn.cpska.data.api

data class Club(val id: Int, val name: String) {
    override fun toString() = name
}