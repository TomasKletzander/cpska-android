package cz.dmn.cpska.ui.common

import android.content.Context
import android.graphics.Typeface

class TypefaceCache {
    companion object {
        val typefaces = mutableMapOf<String, Typeface>()

        fun getTypeface(context: Context, name: String): Typeface {
            if (!typefaces.containsKey(name)) {
                typefaces[name] = Typeface.createFromAsset(context.assets, "fonts/" + name + ".ttf")
            }
            return typefaces[name]!!
        }
    }
}