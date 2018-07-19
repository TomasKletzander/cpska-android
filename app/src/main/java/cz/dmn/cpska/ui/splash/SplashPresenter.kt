package cz.dmn.cpska.ui.splash

import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpPresenter
import javax.inject.Inject

@ActivityScope
class SplashPresenter @Inject constructor() : BaseMvpPresenter<SplashMvp.View>(), SplashMvp.Presenter