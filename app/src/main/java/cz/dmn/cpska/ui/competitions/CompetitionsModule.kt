package cz.dmn.cpska.ui.competitions

import dagger.Binds
import dagger.Module

@Module
interface CompetitionsModule {

    @Binds
    fun bindView(coordinator: CompetitionsCoordinator): CompetitionsMvp.View

    @Binds
    fun bindPresenter(presenter: CompetitionsPresenter): CompetitionsMvp.Presenter
}