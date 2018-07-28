package cz.dmn.cpska.bindings

import android.databinding.BindingAdapter
import android.widget.ImageView
import cz.dmn.cpska.di.GlideApp

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String?) {
    imageUrl?.let {
        GlideApp.with(this)
            .load(it)
            .into(this)
    }
}

@BindingAdapter("roundImageUrl")
fun ImageView.setRoundImageUrl(roundImageUrl: String?) {
    roundImageUrl?.let {
        GlideApp.with(this)
                .load(it)
                .circleCrop()
                .into(this)
    }
}