package cz.dmn.cpska.ui.club

import android.view.View
import com.squareup.coordinators.Coordinator
import com.squareup.coordinators.CoordinatorProvider
import cz.dmn.cpska.R
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import cz.dmn.cpska.ui.leaderboard.LeaderboardCoordinator
import javax.inject.Inject

@PerActivity
class ClubTabsManager @Inject constructor(
    private val flightsCoordinator: FlightsCoordinator,
    private val leaderboardCoordinator: LeaderboardCoordinator
) : CoordinatorProvider {

    override fun provideCoordinator(view: View): Coordinator? = when (view.id) {
        R.id.flights -> flightsCoordinator
        R.id.leaderboard -> leaderboardCoordinator
        else -> null
    }
}