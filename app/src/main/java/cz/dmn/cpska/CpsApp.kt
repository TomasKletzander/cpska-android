package cz.dmn.cpska

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric

class CpsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics(), Answers())
    }
}