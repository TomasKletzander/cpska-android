package cz.dmn.cpska.navigators

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import org.parceler.Parcels

open class BaseNavigator(val activity: AppCompatActivity) {

    fun startActivity(cls: Class<*>, vararg arguments: Pair<String, Any>) {
        activity.startActivity(Intent(activity, cls).also { applyIntentArguments(it, arguments) })
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