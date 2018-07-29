package cz.dmn.cpska.ui.club

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.squareup.coordinators.Coordinators
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.databinding.ActivityClubBinding
import cz.dmn.cpska.extensions.setTypeface
import cz.dmn.cpska.ui.BaseActivity
import cz.dmn.cpska.ui.flights.FlightsMvp
import javax.inject.Inject

class ClubActivity : BaseActivity() {

    @Inject lateinit var club: Club
    @Inject lateinit var tabsManager: ClubTabsManager
    private lateinit var binding: ActivityClubBinding
    @Inject lateinit var flightsPresenter: FlightsMvp.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_club)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = club.name
        binding.toolbar.setTypeface(getString(R.string.fontDecorative))

        Coordinators.installBinder(binding.content, tabsManager)

        flightsPresenter.filterClub = club
    }
}