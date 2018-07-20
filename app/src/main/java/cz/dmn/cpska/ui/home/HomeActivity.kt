package cz.dmn.cpska.ui.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.squareup.coordinators.Coordinator
import com.squareup.coordinators.Coordinators
import cz.dmn.cpska.R
import cz.dmn.cpska.databinding.ActivityHomeBinding
import cz.dmn.cpska.ui.BaseActivity
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import cz.dmn.cpska.ui.leaderboard.LeaderboardCoordinator
import dagger.Lazy
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    lateinit var binding: ActivityHomeBinding
    @Inject lateinit var leaderboardCoordinatorLazy: Lazy<LeaderboardCoordinator>
    @Inject lateinit var flightsCoordinatorLazy: Lazy<FlightsCoordinator>

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigationProfile -> {
                binding.content.setDisplayedChild(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationLeaderboard -> {
                binding.content.setDisplayedChild(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigationFlights -> {
                binding.content.setDisplayedChild(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)

        binding.navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        Coordinators.installBinder(binding.content, this::bindCoordinators)
    }

    private fun bindCoordinators(view: View): Coordinator? {
        return when (view) {
            binding.leaderboard?.root -> leaderboardCoordinatorLazy.get()
            binding.flights?.root -> flightsCoordinatorLazy.get()
            else -> null
        }
    }
}
