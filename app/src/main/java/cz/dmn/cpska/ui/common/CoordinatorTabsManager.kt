package cz.dmn.cpska.ui.common

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import android.widget.ViewAnimator
import com.squareup.coordinators.CoordinatorProvider
import com.squareup.coordinators.Coordinators
import cz.dmn.cpska.extensions.display
import cz.dmn.cpska.extensions.removeShiftMode
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

open class CoordinatorTabsManager(
    private vararg val tabs: TabInfo<*, *, *>
) : CoordinatorProvider, BottomNavigationView.OnNavigationItemSelectedListener {

    class TabInfo<V: MvpView, P: MvpPresenter<V, PresenterState<*>>, out VB: ViewDataBinding>(
        val navigationId: Int,
        val viewId: Int,
        val coordinator: BaseMvpCoordinator<V, P, VB>
    )

    fun install(content: ViewAnimator, navigation: BottomNavigationView) {
        Coordinators.installBinder(content, this)
        navigation.apply {
            setOnNavigationItemSelectedListener(this@CoordinatorTabsManager)
            removeShiftMode()
            content.post {
                onNavigationItemSelected(menu.findItem(selectedItemId))
            }
        }
    }

    fun restoreState(inState: Bundle) {
        tabs.forEach {
            it.coordinator.restoreState(inState)
        }
    }

    fun saveState(outState: Bundle) {
        tabs.forEach {
            it.coordinator.saveState(outState)
        }
    }

    override fun provideCoordinator(view: View) = tabs.find { it.viewId == view.id }?.coordinator

    override fun onNavigationItemSelected(item: MenuItem) = tabs.find { it.navigationId == item.itemId }?.let {
        (it.coordinator.binding.root.parent as ViewAnimator).display(it.coordinator.binding.root)
        true
    } ?: false
}