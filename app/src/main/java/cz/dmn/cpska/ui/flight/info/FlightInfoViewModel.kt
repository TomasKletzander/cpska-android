package cz.dmn.cpska.ui.flight.info

import android.databinding.ObservableField
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class FlightInfoViewModel @Inject constructor() {
    val pilotImageUrl = ObservableField<String>()
    val pilotName = ObservableField<String>()
    val clubName = ObservableField<String>()
    val planeName = ObservableField<String>()
    val distance = ObservableField<String>()
    val speed = ObservableField<String>()
    val points = ObservableField<String>()
    val mapImageUrl = ObservableField<String>()
    val profileImageUrl = ObservableField<String>()
}