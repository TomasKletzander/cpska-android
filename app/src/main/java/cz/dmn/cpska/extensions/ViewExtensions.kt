package cz.dmn.cpska.extensions

import android.view.View
import android.view.ViewTreeObserver

fun View.addOneShotGlobalLayoutListener(listener: () -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            this@addOneShotGlobalLayoutListener.viewTreeObserver.removeOnGlobalLayoutListener(this)
            listener()
        }
    })
}