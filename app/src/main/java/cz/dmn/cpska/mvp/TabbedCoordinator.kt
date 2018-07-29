package cz.dmn.cpska.mvp

import android.databinding.ViewDataBinding
import android.view.Menu
import android.view.MenuItem

abstract class TabbedCoordinator<V: MvpView, P: MvpPresenter<V, PresenterState<*>>, VB: ViewDataBinding> : BaseMvpCoordinator<V, P, VB>() {

    abstract fun onCreateOptionsMenu(menu: Menu)
    abstract fun onOptionsItemSelected(menuItem: MenuItem): Boolean
}