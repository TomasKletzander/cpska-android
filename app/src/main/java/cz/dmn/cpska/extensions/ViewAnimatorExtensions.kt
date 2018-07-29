package cz.dmn.cpska.extensions

import android.view.View
import android.widget.ViewAnimator

fun ViewAnimator.display(child: View) {
    displayedChild = indexOfChild(child)
}