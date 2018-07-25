package cz.dmn.cpska.ui.common

import android.content.Context
import android.content.res.Resources
import android.widget.ArrayAdapter
import cz.dmn.cpska.R
import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class ClubsAdapter @Inject constructor(@ByActivity context: Context, clubsCache: MemoryCache<List<Club>>, @ByActivity res: Resources)
    : ArrayAdapter<Club>(context, R.layout.spinner_item_club, listOf(Club(0, res.getString(R.string.noClubToHighlight))).union(clubsCache.data).toList())