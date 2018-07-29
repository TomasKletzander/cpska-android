package cz.dmn.cpska.ui.splash

import cz.dmn.cpska.CpsApp
import cz.dmn.cpska.R
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import cz.dmn.cpska.navigators.HomeNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerActivity
class SplashPresenter @Inject constructor(
    private val homeNavigator: HomeNavigator,
    private val app: CpsApp,
    override val state: EmptyPresenterState
) : BaseMvpPresenter<SplashMvp.View, PresenterState<*>>(), SplashMvp.Presenter {

    lateinit var initDisposable: Disposable

    override fun attachView(view: SplashMvp.View) {
        super.attachView(view)
        initDisposable = app.initialize()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    homeNavigator.openHome()
                    this.view?.close()
                }, {
                    this.view?.error(R.string.failedToInitializeApp)
                })
    }

    override fun detachView() {
        super.detachView()
        initDisposable.dispose()
    }
}