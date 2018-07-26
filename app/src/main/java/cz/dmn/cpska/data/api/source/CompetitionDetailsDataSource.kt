package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.data.api.CompetitionDetails
import io.reactivex.Observable

interface CompetitionDetailsDataSource {

    fun getCompetitionDetails(id: Int): Observable<CompetitionDetails>
}