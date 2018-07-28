package cz.dmn.cpska.navigators

import android.app.Activity
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.home.HomeActivity
import javax.inject.Inject

@PerActivity
class HomeNavigator @Inject constructor(activity: Activity) : BaseNavigator(activity) {
    fun openHome() {
        startActivity(HomeActivity::class.java)
    }
}