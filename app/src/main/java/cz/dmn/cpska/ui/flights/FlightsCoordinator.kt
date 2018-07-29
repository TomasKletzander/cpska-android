package cz.dmn.cpska.ui.flights

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.brandongogetap.stickyheaders.StickyLayoutManager
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.CoordFlightsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.mvp.TabbedCoordinator
import cz.dmn.cpska.ui.ItemClickListener
import dagger.Lazy
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerActivity
class FlightsCoordinator @Inject constructor(
    @ByActivity private val context: Context,
    private val adapterLazy: Lazy<FlightsAdapter>,
    @ByActivity private val menuInflater: MenuInflater
) : TabbedCoordinator<FlightsMvp.View, FlightsMvp.Presenter, CoordFlightsBinding>(), FlightsMvp.View, ItemClickListener<FlightData> {

    val adapter by lazy { adapterLazy.get() }

    override fun onAttach() {
        binding.adapter = adapter
        binding.listFlights.layoutManager = StickyLayoutManager(context, adapter).also {
            it.elevateHeaders(true)
        }
        binding.listFlights.addOnScrollListener(pagingScrollListener)
    }

    override fun clear() {
        adapter.clear()
    }

    override fun show(data: List<Pair<FlightData, Boolean>>) {
        adapter.replace(data)
    }

    override fun error(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun warning(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
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

    override val requestOpenFlight: Subject<FlightData> = PublishSubject.create()

    override fun onItemClicked(item: FlightData) {
        requestOpenFlight.onNext(item)
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