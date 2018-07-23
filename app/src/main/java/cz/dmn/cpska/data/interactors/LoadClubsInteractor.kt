package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.Club
import io.reactivex.Observable
import javax.inject.Inject

class LoadClubsInteractor @Inject constructor(private val dataManager: DataManager) : BaseInteractor<List<Club>>() {

    override fun buildInteractorObservable(): Observable<List<Club>> = dataManager.getClubs()
}