package cz.dmn.cpska.ui.club

import cz.dmn.cpska.R
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.common.CoordinatorTabsManager
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import javax.inject.Inject

@PerActivity
class ClubTabsManager @Inject constructor(
    flightsCoordinator: FlightsCoordinator
) : CoordinatorTabsManager(
        TabInfo(R.id.navigationFlights, R.id.flights, flightsCoordinator)
)