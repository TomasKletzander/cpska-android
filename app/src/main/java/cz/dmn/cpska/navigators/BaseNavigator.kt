package cz.dmn.cpska.navigators

import android.content.Context
import android.content.Intent

open class BaseNavigator(val context: Context) {

    fun startActivity(cls: Class<*>) {
        context.startActivity(Intent(context, cls))
    }
}