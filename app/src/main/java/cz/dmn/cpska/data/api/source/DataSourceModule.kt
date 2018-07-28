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

    @PerApplication
    @Binds
    fun bindCompetitionDetailsDataSource(source: CompetitionDetailsHtmlDataSource): CompetitionDetailsDataSource

    @PerApplication
    @Binds
    fun bindFlightDetailsDataSource(source: FlightDetailsHtmlDataSource): FlightDetailsDataSource
}