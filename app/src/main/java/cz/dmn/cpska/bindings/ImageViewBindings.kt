package cz.dmn.cpska.bindings

import android.databinding.BindingAdapter
import android.widget.ImageView
import cz.dmn.cpska.di.GlideApp

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    GlideApp.with(this)
            .load(imageUrl)
            .into(this)
}