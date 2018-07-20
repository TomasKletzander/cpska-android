package cz.dmn.cpska.ui.leaderboard

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.dmn.cpska.data.api.leaderboard.LeaderboardRow
import cz.dmn.cpska.databinding.LeaderboardRowBinding
import cz.dmn.cpska.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class LeaderboardAdapter @Inject constructor(private val inflater: LayoutInflater) : RecyclerView.Adapter<LeaderboardRowViewHolder>() {

    val items = mutableListOf<LeaderboardRow>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LeaderboardRowViewHolder(LeaderboardRowBinding.inflate(inflater, parent, false))

    override fun onBindViewHolder(holder: LeaderboardRowViewHolder, position: Int) {

    }
}