package cz.dmn.cpska.ui.leaderboard

import android.view.Menu
import android.view.MenuItem
import android.view.View
import cz.dmn.cpska.data.api.leaderboard.LeaderboardRow
import cz.dmn.cpska.databinding.CoordLeaderboardBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import cz.dmn.cpska.mvp.TabbedCoordinator
import javax.inject.Inject

@PerActivity
class LeaderboardCoordinator @Inject constructor(private val adapter: LeaderboardAdapter, private val binding: CoordLeaderboardBinding)
    : TabbedCoordinator<LeaderboardMvp.View, LeaderboardMvp.Presenter>(), LeaderboardMvp.View {

    override fun attach(view: View) {
        super.attach(view)
        binding.adapter = adapter
    }

    override fun show(rows: List<LeaderboardRow>) {
        adapter.items.clear()
        adapter.items.addAll(rows)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu) {
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}