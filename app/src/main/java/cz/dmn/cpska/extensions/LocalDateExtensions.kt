package cz.dmn.cpska.extensions

import org.joda.time.LocalDate

fun LocalDate.isToday() = LocalDate.now().isEqual(this)

fun LocalDate.isYesterday() = LocalDate.now().minusDays(1).isEqual(this)