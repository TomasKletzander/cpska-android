package cz.dmn.cpska.ui.flights

import android.content.Context
import android.content.SharedPreferences
import android.support.design.widget.Snackbar
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.brandongogetap.stickyheaders.StickyLayoutManager
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.CoordFlightsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.di.FlightsPreferences
import cz.dmn.cpska.extensions.addOneShotGlobalLayoutListener
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import cz.dmn.cpska.mvp.TabbedCoordinator
import cz.dmn.cpska.ui.common.ClubsAdapter
import cz.dmn.cpska.ui.common.ClubsAdapter_Factory
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerActivity
class FlightsCoordinator @Inject constructor(
    @ByActivity private val context: Context,
    private val adapter: FlightsAdapter,
    private val binding: CoordFlightsBinding,
    @ByActivity private val menuInflater: MenuInflater,
    private val settingsModel: FlightsSettingsModel,
    private val clubsAdapter: ClubsAdapter
) : TabbedCoordinator<FlightsMvp.View, FlightsMvp.Presenter>(), FlightsMvp.View {

    init {
        settingsModel.selectedClubSubject.subscribe {
            adapter.setHighlightedClub(it.data)
        }
    }

    var settingsFrameOffset = 0f

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
        binding.settingsModel = settingsModel
        binding.listFlights.layoutManager = StickyLayoutManager(context, adapter).also {
            it.elevateHeaders(true)
        }
        binding.listFlights.addOnScrollListener(pagingScrollListener)
        binding.settings.setOnClickListener {
            showSettings()
        }
        binding.setCloseSettingsListener {
            hideSettings()
        }
        binding.clubs.adapter = clubsAdapter
        binding.settingsFrame.addOneShotGlobalLayoutListener {
            settingsFrameOffset = binding.settingsFrame.height.toFloat() +
                    (binding.settingsFrame.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin
            binding.settingsFrame.translationY = settingsFrameOffset
            binding.settingsFrame.visibility = View.GONE
        }
    }

    override fun clear() {
        adapter.clear()
    }

    override fun addPage(pageData: List<FlightData>) {
        adapter.add(pageData)
    }

    override fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override var loading: Boolean
        get() = adapter.loading
        set(value) {
            adapter.loading = value
        }

    override val requestNextPage = PublishSubject.create<Any>()

    override val requestRefresh = PublishSubject.create<Any>()

    override fun onCreateOptionsMenu(menu: Menu) {
        menuInflater.inflate(R.menu.flights_options, menu)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.refresh -> {
                requestRefresh.onNext(Any())
                true
            }
            else -> false
        }
    }

    private fun showSettings() {
        if (binding.settingsFrame.visibility != View.VISIBLE) {
            binding.settingsFrame.visibility = View.VISIBLE
            binding.settingsFrame.animate()
                    .translationY(0f)
                    .setDuration(200)
                    .setInterpolator(LinearOutSlowInInterpolator())
            binding.settings.hide()
        }
    }

    private fun hideSettings() {
        if (binding.settingsFrame.visibility == View.VISIBLE) {
            binding.settingsFrame.animate()
                    .translationY(settingsFrameOffset)
                    .setDuration(200)
                    .setInterpolator(LinearOutSlowInInterpolator())
                    .withEndAction {
                        binding.settingsFrame.visibility = View.GONE
                        binding.settings.show()
                    }
        }
    }

    private val pagingScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            if (dy < 0) return
            val lastPosition = (recyclerView!!.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            // Triggers an event when the last card or the loader indicator is visible.
            if (lastPosition >= adapter.itemCount - 2) {
                requestNextPage.onNext(Any())
            }
        }
    }
}