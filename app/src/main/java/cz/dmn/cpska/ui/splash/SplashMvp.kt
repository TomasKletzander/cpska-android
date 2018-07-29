package cz.dmn.cpska.ui.splash

import android.support.annotation.StringRes
import cz.dmn.cpska.mvp.MvpPresenter
import cz.dmn.cpska.mvp.MvpView
import cz.dmn.cpska.mvp.PresenterState

interface SplashMvp {

    interface View: MvpView {
        fun close()
        fun error(@StringRes messageId: Int)
    }

    interface Presenter: MvpPresenter<View, PresenterState<*>>
}