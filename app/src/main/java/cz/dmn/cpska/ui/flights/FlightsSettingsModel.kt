package cz.dmn.cpska.ui.flights

import android.content.SharedPreferences
import android.view.View
import android.widget.AdapterView
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.di.FlightsPreferences
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.util.Optional
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerActivity
class FlightsSettingsModel @Inject constructor(
    private val clubs: List<Club>,
    @FlightsPreferences private val prefs: SharedPreferences
) {

    private var selectedClub: Club? = findInitialClub()

    val onClubSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            selectedClub = null
            prefs.edit().putInt(PrefKeys.HighlightedClubId, 0).apply()
            selectedClubSubject.onNext(Optional())
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (position == 0) {
                selectedClub = null
            } else {
                selectedClub = clubs[position - 1]
            }
            prefs.edit().putInt(PrefKeys.HighlightedClubId, selectedClub?.let { it.id } ?: 0).apply()
            selectedClubSubject.onNext(Optional(selectedClub))
        }
    }

    val clubSelection: Int
        get() = selectedClub?.let { clubs.indexOf(it) + 1 } ?: 0

    val selectedClubSubject: Subject<Optional<Club>> = PublishSubject.create()

    private fun findInitialClub(): Club? {
        clubs.forEach {
            if (it.id == prefs.getInt(PrefKeys.HighlightedClubId, 0)) return it
        }
        return null
    }

    object PrefKeys {
        val HighlightedClubId = "HIGHLIGHTED_CLUB_ID"
    }
}