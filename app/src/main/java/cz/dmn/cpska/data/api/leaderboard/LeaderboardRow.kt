package cz.dmn.cpska.data.api.leaderboard

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.User

data class LeaderboardRow(val rank: Int, val points: Int, val user: User, val club: Club)