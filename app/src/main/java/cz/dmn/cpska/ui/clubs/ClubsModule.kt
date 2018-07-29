package cz.dmn.cpska.ui.clubs

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.ui.ItemClickListener
import cz.dmn.cpska.ui.common.ItemToggleListener
import dagger.Binds
import dagger.Module

@Module
interface ClubsModule {

    @Binds
    fun bindView(coordinator: ClubsCoordinator): ClubsMvp.View

    @Binds
    fun bindPresenter(presenter: ClubsPresenter): ClubsMvp.Presenter

    @Binds
    fun bindClubClickListener(coordinator: ClubsCoordinator): ItemClickListener<Club>

    @Binds
    fun bindClubToggleListener(coordinator: ClubsCoordinator): ItemToggleListener<Club>
}