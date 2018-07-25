package cz.dmn.cpska.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.squareup.coordinators.Coordinator
import com.squareup.coordinators.Coordinators
import cz.dmn.cpska.R
import cz.dmn.cpska.databinding.ActivityHomeBinding
import cz.dmn.cpska.extensions.removeShiftMode
import cz.dmn.cpska.extensions.setTypeface
import cz.dmn.cpska.mvp.TabbedCoordinator
import cz.dmn.cpska.ui.BaseActivity
import cz.dmn.cpska.ui.competitions.CompetitionsCoordinator
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import cz.dmn.cpska.ui.leaderboard.LeaderboardCoordinator
import dagger.Lazy
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_home.view.flights
import kotlinx.android.synthetic.main.activity_home.view.leaderboard
import kotlinx.android.synthetic.main.activity_home.view.profile
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    object Tabs {
        val Flights = 0
        val Leaderboard = 1
        val Competitions = 2
        val Profile = 3
    }

    lateinit var binding: ActivityHomeBinding
    @Inject lateinit var leaderboardCoordinatorLazy: Lazy<LeaderboardCoordinator>
    val leaderboardCoordinator by lazy { leaderboardCoordinatorLazy.get() }
    @Inject lateinit var flightsCoordinatorLazy: Lazy<FlightsCoordinator>
    val flightsCoordinator by lazy { flightsCoordinatorLazy.get() }
    @Inject lateinit var competitionsCoordinatorLazy: Lazy<CompetitionsCoordinator>
    val competitionsCoordinator by lazy { competitionsCoordinatorLazy.get() }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigationProfile -> binding.content.displayedChild = Tabs.Profile
            R.id.navigationLeaderboard -> binding.content.displayedChild = Tabs.Leaderboard
            R.id.navigationFlights -> binding.content.displayedChild = Tabs.Flights
            R.id.navigationCompetitions -> binding.content.displayedChild = Tabs.Competitions
        }
        invalidateOptionsMenu()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setSubtitle(R.string.appSubtitle)
        binding.toolbar.setTypeface(resources.getString(R.string.fontDecorative))

        binding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        binding.navigation.removeShiftMode()

        Coordinators.installBinder(binding.content, this::bindCoordinators)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        when (binding.content.displayedChild) {
            Tabs.Flights -> flightsCoordinator.onCreateOptionsMenu(menu)
            Tabs.Leaderboard -> leaderboardCoordinator.onCreateOptionsMenu(menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (super.onOptionsItemSelected(item)) return true
        return when (binding.content.displayedChild) {
            Tabs.Flights -> flightsCoordinator.onOptionsItemSelected(item)
            Tabs.Leaderboard -> leaderboardCoordinator.onOptionsItemSelected(item)
            else -> false
        }
    }

    private fun bindCoordinators(view: View): Coordinator? {
        return when (view) {
            binding.leaderboard?.root -> leaderboardCoordinator
            binding.flights?.root -> flightsCoordinator
            binding.competitions?.root -> competitionsCoordinator
            else -> null
        }
    }
}
