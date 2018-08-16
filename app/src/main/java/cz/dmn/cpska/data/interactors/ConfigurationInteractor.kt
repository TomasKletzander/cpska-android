package cz.dmn.cpska.data.interactors

import cz.dmn.cpska.data.DataManager
import cz.dmn.cpska.data.api.cfg.Configuration
import cz.dmn.cpska.di.PerActivity
import javax.inject.Inject

@PerActivity
class ConfigurationInteractor @Inject constructor(private val dataManager: DataManager) : BaseInteractor<Configuration>() {
    override fun buildInteractorObservable() = dataManager.getConfiguration()
}