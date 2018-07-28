package cz.dmn.cpska.ui.flight

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.squareup.coordinators.Coordinators
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.FlightDetails
import cz.dmn.cpska.databinding.ActivityFlightBinding
import cz.dmn.cpska.extensions.setTypeface
import cz.dmn.cpska.mvp.BaseMvpActivity
import javax.inject.Inject

class FlightActivity : BaseMvpActivity<FlightMvp.View, FlightMvp.Presenter>(), FlightMvp.View {

    private lateinit var binding: ActivityFlightBinding
    @Inject lateinit var tabsManager: FlightTabsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flight)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.subtitle = getString(R.string.flight)
        binding.toolbar.setTypeface(getString(R.string.fontDecorative))

        Coordinators.installBinder(binding.content, tabsManager)
    }

    override var loading: Boolean
        get() = false
        set(value) {}

    override fun show(flightDetails: FlightDetails) {
    }

    override fun error(message: String) {
    }
}