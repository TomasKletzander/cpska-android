package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.Competition
import io.reactivex.Observable

interface CompetitionsDataSource {
    fun getCompetitions(): Observable<List<Competition>>
}