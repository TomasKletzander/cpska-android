package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.Club
import io.reactivex.Observable

interface ClubsDataSource {
    fun getClubs(): Observable<List<Club>>
}