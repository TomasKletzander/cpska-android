package cz.dmn.cpska.extensions

import android.view.ViewGroup
import android.widget.TextView
import cz.dmn.cpska.bindings.setFont

fun ViewGroup.setTypeface(font: String) {
    for (idx in 0 until childCount) {
        val child = getChildAt(idx)
        (child as? TextView)?.setFont(font)
        (child as? ViewGroup)?.setTypeface(font)
    }
}