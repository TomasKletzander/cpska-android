package cz.dmn.cpska.mvp

import android.view.Menu
import android.view.MenuItem

abstract class TabbedCoordinator<V: MvpView, P: MvpPresenter<V>> : BaseMvpCoordinator<V, P>() {

    abstract fun onCreateOptionsMenu(menu: Menu)
    abstract fun onOptionsItemSelected(menuItem: MenuItem): Boolean
}