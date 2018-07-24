package cz.dmn.cpska.extensions

import org.jsoup.nodes.Element

fun Element.extractIdFromInnerHref(): Int {
    val links = this.getElementsByTag("a")
    if (links.size == 0) return 0
    val href = links[0].attr("href")
    val parts = href.split("=")
    return parts.lastOrNull()?.toInt() ?: 0
}