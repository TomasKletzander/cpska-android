package cz.dmn.cpska.ui.home

import android.content.Context
import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.navigators.BaseNavigator
import javax.inject.Inject

@ActivityScope
class HomeNavigator @Inject constructor(context: Context) : BaseNavigator(context) {
    fun openHome() {
        startActivity(HomeActivity::class.java)
    }
}