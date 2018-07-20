package cz.dmn.cpska.ui.leaderboard

import android.view.View
import cz.dmn.cpska.data.api.leaderboard.LeaderboardRow
import cz.dmn.cpska.databinding.CoordLeaderboardBinding
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import javax.inject.Inject

@ActivityScope
class LeaderboardCoordinator @Inject constructor(private val adapter: LeaderboardAdapter, private val binding: CoordLeaderboardBinding) : BaseMvpCoordinator<LeaderboardMvp.View, LeaderboardMvp.Presenter>(), LeaderboardMvp.View {

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
    }

    override fun show(rows: List<LeaderboardRow>) {
        adapter.items.clear()
        adapter.items.addAll(rows)
        adapter.notifyDataSetChanged()
    }
}