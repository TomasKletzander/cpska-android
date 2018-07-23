package cz.dmn.cpska.ui.flights

import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.DateHeaderRowBinding
import cz.dmn.cpska.databinding.FlightsRowBinding
import cz.dmn.cpska.databinding.LoaderRowBinding
import cz.dmn.cpska.di.HighlightedBackground
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.common.DateHeaderViewHolder
import cz.dmn.cpska.ui.common.DateHeaderViewModel
import cz.dmn.cpska.ui.common.LoaderViewHolder
import cz.dmn.cpska.ui.common.LoaderViewModel
import cz.dmn.cpska.util.RecentDateFormatter
import org.joda.time.LocalDate
import javax.inject.Inject

@PerActivity
class FlightsAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val dateFormatter: RecentDateFormatter,
    @HighlightedBackground private val highlightedBackground: Drawable
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderHandler {

    private val flights = mutableListOf<FlightViewModel>()
    private val allItems = mutableListOf<Any>()
    private var loadingFlag = false
    private var highlightedClub: Club? = null

    override fun getItemCount() = allItems.size

    override fun getItemViewType(position: Int) = when (allItems[position]) {
        is FlightViewModel -> ViewTypes.Flight
        is LoaderViewModel -> ViewTypes.Loader
        is DateHeaderViewModel -> ViewTypes.Header
        else -> throw RuntimeException("Unexpected item type " + allItems[position].javaClass.name + " at position " + position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.Flight -> FlightRowViewHolder(FlightsRowBinding.inflate(layoutInflater, parent, false))
            ViewTypes.Loader -> LoaderViewHolder(LoaderRowBinding.inflate(layoutInflater, parent, false))
            ViewTypes.Header -> DateHeaderViewHolder(DateHeaderRowBinding.inflate(layoutInflater, parent, false))
            else -> throw RuntimeException("Unexpected type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        allItems[position].let {
            when (it) {
                is FlightViewModel -> (holder as? FlightRowViewHolder)?.binding?.viewModel = it
                is DateHeaderViewModel -> (holder as? DateHeaderViewHolder)?.binding?.viewModel = it
            }
        }
    }

    override fun getAdapterData(): MutableList<*> = allItems

    var loading: Boolean
        get() = loadingFlag
        set(value) {
            loadingFlag = value
            reloadAllItems()
        }

    fun clear() {
        allItems.clear()
        flights.clear()
        reloadAllItems()
    }

    fun add(data: List<FlightData>) {
        flights.addAll(data.map {
            FlightViewModel(it)
        })
        reloadAllItems()
    }

    private fun reloadAllItems() {
        allItems.clear()
        var lastDate : LocalDate? = null
        flights.forEach {
            it.background = null
            highlightedClub?.apply {
                if (it.club == name) {
                    it.background = highlightedBackground
                }
            }
            if (lastDate == null || !it.date.isEqual(lastDate)) {
                lastDate = it.date
                allItems.add(DateHeaderViewModel(dateFormatter.format(it.date)))
            }
            allItems.add(it)
        }
        if (loadingFlag) {
            allItems.add(LoaderViewModel)
        }
        notifyDataSetChanged()
    }

    fun setHighlightedClub(club: Club?) {
        highlightedClub = club
        reloadAllItems()
    }

    object ViewTypes {
        val Flight = 0
        val Header = 1
        val Loader = 2
    }
}