package cz.dmn.cpska.ui.home

import android.content.Context
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.di.ByActivity
import cz.dmn.cpska.navigators.BaseNavigator
import javax.inject.Inject

@PerActivity
class HomeNavigator @Inject constructor(@ByActivity context: Context) : BaseNavigator(context) {
    fun openHome() {
        startActivity(HomeActivity::class.java)
    }
}