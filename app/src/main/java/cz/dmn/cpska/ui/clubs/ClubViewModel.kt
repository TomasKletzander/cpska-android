package cz.dmn.cpska.ui.clubs

import android.databinding.ObservableBoolean
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.ui.common.AdapterItem

class ClubViewModel(val club: Club, favoriteFlag: Boolean) : AdapterItem {

    val clubName = club.name
    val favorite = ObservableBoolean(favoriteFlag)

    override fun isSameAs(other: AdapterItem) = (other as? ClubViewModel)?.club?.id == club.id

    override fun isSameContentAs(other: AdapterItem) = (other as? ClubViewModel)?.let { it.club.id == club.id && it.favorite.get() == favorite.get() } ?: false
}