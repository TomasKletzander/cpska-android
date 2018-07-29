package cz.dmn.cpska.ui.home

import cz.dmn.cpska.R
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.clubs.ClubsCoordinator
import cz.dmn.cpska.ui.common.CoordinatorTabsManager
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import javax.inject.Inject

@PerActivity
class HomeTabsManager @Inject constructor(
    flightsCoordinator: FlightsCoordinator,
    clubsCoordinator: ClubsCoordinator
) : CoordinatorTabsManager(
        TabInfo(R.id.navigationFlights, R.id.flights, flightsCoordinator),
        TabInfo(R.id.navigationClubs, R.id.clubs, clubsCoordinator)
)