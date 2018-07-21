package cz.dmn.cpska.ui.flights

import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.CoordFlightsBinding
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@ActivityScope
class FlightsCoordinator @Inject constructor(private val adapter: FlightsAdapter, private val binding: CoordFlightsBinding)
    : BaseMvpCoordinator<FlightsMvp.View, FlightsMvp.Presenter>(), FlightsMvp.View {

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
        binding.listFlights.addOnScrollListener(pagingScrollListener)
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
        get() = false
        set(value) {}

    override val requestNextPage = PublishSubject.create<Any>()

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