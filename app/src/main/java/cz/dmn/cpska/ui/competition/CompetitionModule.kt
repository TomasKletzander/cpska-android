package cz.dmn.cpska.ui.competition

import cz.dmn.cpska.data.api.Competition
import dagger.Module
import dagger.Provides
import org.parceler.Parcels

@Module
abstract class CompetitionModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideParameter(activity: CompetitionActivity): Competition = Parcels.unwrap(activity.intent.getParcelableExtra(CompetitionConstants.EXTRA_COMPETITION))
    }
}