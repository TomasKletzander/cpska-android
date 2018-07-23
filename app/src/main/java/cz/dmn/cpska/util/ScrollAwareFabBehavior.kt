package cz.dmn.cpska.util

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class ScrollAwareFabBehavior(context: Context, attrSet: AttributeSet) : FloatingActionButton.Behavior() {

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        return true
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: FloatingActionButton, dependency: View) = dependency is RecyclerView

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                       child: FloatingActionButton, target: View, dxConsumed: Int,
                       dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed)
        if (dyConsumed > 0 && child.translationY == 0f) {
            child.animate().translationY(child.height.toFloat() + (child.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin).setDuration(300)
        } else if (dyConsumed < 0 && child.translationY != 0f) {
            child.animate().translationY(0f).setDuration(100)
        }
    }
}