package cz.dmn.cpska.ui.splash

import android.os.Bundle
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
}