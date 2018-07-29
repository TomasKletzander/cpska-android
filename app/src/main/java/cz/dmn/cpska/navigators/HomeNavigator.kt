package cz.dmn.cpska.navigators

import android.support.v7.app.AppCompatActivity
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.ui.home.HomeActivity
import javax.inject.Inject

@PerActivity
class HomeNavigator @Inject constructor(activity: AppCompatActivity) : BaseNavigator(activity) {
    fun openHome() {
        startActivity(HomeActivity::class.java)
    }
}