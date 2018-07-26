package cz.dmn.cpska.navigators

import android.content.Context
import android.content.Intent
import org.parceler.Parcels

open class BaseNavigator(val context: Context) {

    fun startActivity(cls: Class<*>, vararg arguments: Pair<String, Any>) {
        context.startActivity(Intent(context, cls).also { applyIntentArguments(it, arguments) })
    }

    private fun applyIntentArguments(intent: Intent, arguments: Array<out Pair<String, Any>>) {
        arguments.forEach {
            val value = it.second
            when (value) {
                is String -> intent.putExtra(it.first, value)
                is Int -> intent.putExtra(it.first, value)
                else -> intent.putExtra(it.first, Parcels.wrap(value))
            }
        }
    }
}