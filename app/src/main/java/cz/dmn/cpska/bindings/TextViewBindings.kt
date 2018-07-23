package cz.dmn.cpska.bindings

import android.databinding.BindingAdapter
import android.widget.TextView
import cz.dmn.cpska.ui.common.TypefaceCache

@BindingAdapter("font")
fun TextView.setFont(font: String) {
    this.typeface = TypefaceCache.getTypeface(context, font)
}