package cz.dmn.cpska.navigators

import android.app.Activity
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.club.ClubActivity
import cz.dmn.cpska.ui.club.ClubConstants
import javax.inject.Inject

@PerActivity
class ClubNavigator @Inject constructor(activity: Activity) : BaseNavigator(activity) {

    fun navigateToClub(club: Club) = startActivity(ClubActivity::class.java, Pair(ClubConstants.EXTRA_CLUB, club))
}