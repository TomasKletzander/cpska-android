package cz.dmn.cpska.bindings

import android.databinding.BindingAdapter
import android.widget.AdapterView
import android.widget.Spinner

@BindingAdapter("onItemSelected")
fun Spinner.setItemSelectedListener(itemSelectedListener: AdapterView.OnItemSelectedListener?) {
    this.onItemSelectedListener = itemSelectedListener
}

@BindingAdapter("selection")
fun Spinner.setValue(selection: Int?) {
    selection?.let { this.setSelection(it) }
}