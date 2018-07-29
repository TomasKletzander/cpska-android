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
import cz.dmn.cpska.ui.BaseActivity
import cz.dmn.cpska.ui.clubs.ClubsCoordinator
import cz.dmn.cpska.ui.flights.FlightsCoordinator
import cz.dmn.cpska.ui.leaderboard.LeaderboardCoordinator
import dagger.Lazy
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    lateinit var binding: ActivityHomeBinding
    @Inject lateinit var tabsManager: HomeTabsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setSubtitle(R.string.appSubtitle)
        binding.toolbar.setTypeface(resources.getString(R.string.fontDecorative))

        tabsManager.install(binding.content, binding.navigation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let { tabsManager.restoreState(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        tabsManager.saveState(outState)
    }
}
