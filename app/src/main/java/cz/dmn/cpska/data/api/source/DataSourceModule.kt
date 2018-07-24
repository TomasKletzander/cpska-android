package cz.dmn.cpska.data.api.source

import cz.dmn.cpska.di.PerApplication
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @PerApplication
    @Binds
    fun bindClubsDataSource(source: ClubsHtmlDataSource): ClubsDataSource

    @PerApplication
    @Binds
    fun bindCompetitionsDataSource(source: CompetitionsHtmlDataSource): CompetitionsDataSource

    @PerApplication
    @Binds
    fun bindFlightsDataSource(source: FlightsHtmlDataSource): FlightsDataSource
}