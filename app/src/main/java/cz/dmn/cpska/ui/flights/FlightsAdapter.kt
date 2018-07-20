package cz.dmn.cpska.ui.flights

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.databinding.FlightsRowBinding
import cz.dmn.cpska.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class FlightsAdapter @Inject constructor(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<FlightRowViewHolder>() {

    private val flights = mutableListOf<FlightViewModel>()

    override fun getItemCount() = flights.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FlightRowViewHolder(FlightsRowBinding.inflate(layoutInflater, parent, false))

    override fun onBindViewHolder(holder: FlightRowViewHolder, position: Int) {
        holder.binding.viewModel = flights[position]
    }

    fun load(data: List<FlightData>) {
        flights.clear()
        flights.addAll(data.map {
            FlightViewModel(it)
        })
    }
}