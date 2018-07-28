package cz.dmn.cpska.ui.flight

import android.app.Activity
import cz.dmn.cpska.data.api.FlightData
import cz.dmn.cpska.di.BaseActivityModule
import cz.dmn.cpska.ui.flight.info.FlightInfoModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.parceler.Parcels

@Module(includes = [BaseActivityModule::class, FlightInfoModule::class])
abstract class FlightModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideParameters(activity: FlightActivity): FlightData = Parcels.unwrap(activity.intent.getParcelableExtra(FlightConstants.EXTRA_FLIGHT))
    }

    @Binds
    abstract fun bindActivity(activity: FlightActivity): Activity

    @Binds
    abstract fun bindView(activity: FlightActivity): FlightMvp.View

    @Binds
    abstract fun bindPresenter(presenter: FlightPresenter): FlightMvp.Presenter
}