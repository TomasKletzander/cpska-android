package cz.dmn.cpska.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.Club
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: CpsApp) {

    @Provides
    fun provideApplication(): CpsApp = app

    @Provides
    @ByApplication
    fun provideAppContext(): Context = app

    @Provides
    @ByApplication
    fun provideResources(): Resources = app.resources

    @Provides
    fun provideClubs(): List<Club> = app.clubs

    @Provides
    @HighlightedBackground
    fun provideHighlightedBackground(@ByApplication res: Resources): Drawable = ColorDrawable(res.getColor(R.color.highlightBackground))

    @Provides
    @PerApplication
    @FlightsPreferences
    fun provideFlightsPreferences(): SharedPreferences = app.getSharedPreferences("Flights", Context.MODE_PRIVATE)
}