package cz.dmn.cpska.ui.splash

import android.support.v7.app.AlertDialog
import cz.dmn.cpska.R
import cz.dmn.cpska.mvp.BaseMvpActivity

class SplashActivity : BaseMvpActivity<SplashMvp.View, SplashMvp.Presenter>(), SplashMvp.View {

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