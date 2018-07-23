package cz.dmn.cpska.ui.splash

import android.os.Bundle
import android.support.v7.app.AlertDialog
import cz.dmn.cpska.R
import cz.dmn.cpska.mvp.BaseMvpActivity
import dagger.android.AndroidInjection

class SplashActivity : BaseMvpActivity<SplashMvp.View, SplashMvp.Presenter>(), SplashMvp.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun close() {
        finish()
    }

    override fun error(messageId: Int) {
        AlertDialog.Builder(this)
                .setTitle(R.string.titleError)
                .setMessage(messageId)
                .setNeutralButton(R.string.ok, null)
                .setOnDismissListener {
                    finish()
                }
                .show()
    }
}