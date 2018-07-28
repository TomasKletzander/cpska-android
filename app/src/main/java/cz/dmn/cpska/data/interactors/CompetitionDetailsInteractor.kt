package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class CompetitionDetailsInteractor @Inject constructor(private val dataManager: DataManager)
    : BaseInteractor<CompetitionDetails>() {

    var competitionId: Int = 0
    override fun buildInteractorObservable() = dataManager.getCompetitionDetails(competitionId)
}