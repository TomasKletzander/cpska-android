package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.databinding.CoordClubsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import javax.inject.Inject

@PerActivity
class ClubsCoordinator @Inject constructor(private val adapter: ClubsAdapter)
    : BaseMvpCoordinator<ClubsMvp.View, ClubsMvp.Presenter, CoordClubsBinding>(), ClubsMvp.View {

    override fun onAttach() {
        binding.adapter = adapter
    }

    override fun show(clubs: List<Club>) {
        adapter.update(clubs.map {
            ClubViewModel(it, false)
        })
    }
}