package cz.dmn.cpska.util

import android.content.res.Resources
import cz.dmn.cpska.R
import cz.dmn.cpska.di.ByApplication
import cz.dmn.cpska.di.PerApplication
import cz.dmn.cpska.extensions.isToday
import cz.dmn.cpska.extensions.isYesterday
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

@PerApplication
class RecentDateFormatter @Inject constructor(@ByApplication private val res: Resources) {

    private val formatter = DateTimeFormat.forPattern("E dd. MM. yyyy")

    fun format(date: LocalDate): String {
        if (date.isToday()) return res.getString(R.string.today)
        if (date.isYesterday()) return res.getString(R.string.yesterday)
        return formatter.print(date)
    }
}