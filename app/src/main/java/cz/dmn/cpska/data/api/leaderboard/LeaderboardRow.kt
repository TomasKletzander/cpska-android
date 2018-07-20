package cz.dmn.cpska.data.api.leaderboard

data class LeaderboardRow(val rank: Int, val points: Int, val user: User, val club: Club)