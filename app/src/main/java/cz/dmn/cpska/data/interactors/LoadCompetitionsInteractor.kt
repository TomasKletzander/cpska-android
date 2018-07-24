package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.Competition
import io.reactivex.Observable
import javax.inject.Inject

class LoadCompetitionsInteractor @Inject constructor(private val dataManager: DataManager)
    : BaseInteractor<List<Competition>>() {

    override fun buildInteractorObservable(): Observable<List<Competition>> = dataManager.getCompetitions()
}