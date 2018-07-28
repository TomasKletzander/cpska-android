package cz.dmn.cpska.ui.clubs

import android.databinding.ObservableBoolean
import android.view.View
import cz.dmn.cpska.data.api.Club

class ClubViewModel(club: Club, favoriteFlag: Boolean) {

    val clubName = club.name
    val favorite = ObservableBoolean(favoriteFlag)

    val toggleFavorite = View.OnClickListener { favorite.set(!favorite.get()) }
}