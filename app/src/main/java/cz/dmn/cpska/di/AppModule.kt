package cz.dmn.cpska.di

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.R
import cz.dmn.cpska.data.api.cfg.Configuration
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
    @HighlightedBackground
    fun provideHighlightedBackground(@ByApplication res: Resources): Drawable = ColorDrawable(res.getColor(R.color.highlightBackground))

    @Provides
    fun provideConfiguration(): Configuration = app.configuration
}