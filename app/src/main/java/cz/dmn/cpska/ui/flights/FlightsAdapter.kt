package cz.dmn.cpska.ui.flights

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.DateHeaderRowBinding
import cz.dmn.cpska.databinding.FlightsRowBinding
import cz.dmn.cpska.databinding.LoaderRowBinding
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.di.HighlightedBackground
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.ItemClickListener
import cz.dmn.cpska.ui.common.AdapterItem
import cz.dmn.cpska.ui.common.DateHeaderViewHolder
import cz.dmn.cpska.ui.common.DateHeaderViewModel
import cz.dmn.cpska.ui.common.ItemDiffCallback
import cz.dmn.cpska.ui.common.LoaderViewHolder
import cz.dmn.cpska.ui.common.LoaderViewModel
import cz.dmn.cpska.util.RecentDateFormatter
import org.joda.time.LocalDate
import javax.inject.Inject

@PerActivity
class FlightsAdapter @Inject constructor(
    private val layoutInflater: LayoutInflater,
    private val dateFormatter: RecentDateFormatter,
    @HighlightedBackground private val highlightedBackground: Drawable,
    @ByActivity private val res: Resources,
    private val flightClickListener: ItemClickListener<FlightData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderHandler {

    private val flights = mutableListOf<Pair<FlightData, Boolean>>()
    private val allItems = mutableListOf<AdapterItem>()
    private var loadingFlag = false

    override fun getItemCount() = allItems.size

    override fun getItemViewType(position: Int) = when (allItems[position]) {
        is FlightViewModel -> ViewTypes.Flight
        is LoaderViewModel -> ViewTypes.Loader
        is DateHeaderViewModel -> ViewTypes.Header
        else -> throw RuntimeException("Unexpected item type " + allItems[position].javaClass.name + " at position " + position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.Flight -> FlightRowViewHolder(FlightsRowBinding.inflate(layoutInflater, parent, false), flightClickListener)
            ViewTypes.Loader -> LoaderViewHolder(LoaderRowBinding.inflate(layoutInflater, parent, false))
            ViewTypes.Header -> DateHeaderViewHolder(DateHeaderRowBinding.inflate(layoutInflater, parent, false))
            else -> throw RuntimeException("Unexpected type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        allItems[position].apply {
            when (this) {
                is FlightViewModel -> (holder as? FlightRowViewHolder)?.bind(this)
                is DateHeaderViewModel -> (holder as? DateHeaderViewHolder)?.binding?.viewModel = this
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

    fun replace(data: List<Pair<FlightData, Boolean>>) {
        flights.clear()
        flights.addAll(data)
        reloadAllItems()
    }

    private fun reloadAllItems() {
        val itemsToAdd = mutableListOf<AdapterItem>()
        var lastDate : LocalDate? = null
        flights.forEach {
            val flightData = it.first
            val highlighted = it.second
            val model = FlightViewModel(flightData, res)
            if (highlighted) {
                model.background = highlightedBackground
            }
            if (lastDate == null || !flightData.date.isEqual(lastDate)) {
                lastDate = flightData.date
                itemsToAdd.add(DateHeaderViewModel(dateFormatter.format(flightData.date)))
            }
            itemsToAdd.add(model)
        }
        if (loadingFlag) {
            itemsToAdd.add(LoaderViewModel)
        }
        if (allItems.isEmpty()) {
            allItems.addAll(itemsToAdd)
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(ItemDiffCallback(itemsToAdd, allItems))
            allItems.clear()
            allItems.addAll(itemsToAdd)
            //  For some reason this call causes first display to scroll down few rows. Will use diff once I figure out what's wrong...
            //diffResult.dispatchUpdatesTo(this)
            notifyDataSetChanged()
        }
    }

    object ViewTypes {
        val Flight = 0
        val Header = 1
        val Loader = 2
    }
}