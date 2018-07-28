package cz.dmn.cpska.ui.clubs

import dagger.Binds
import dagger.Module

@Module
interface ClubsModule {

    @Binds
    fun bindView(coordinator: ClubsCoordinator): ClubsMvp.View

    @Binds
    fun bindPresenter(presenter: ClubsPresenter): ClubsMvp.Presenter
}