package cz.dmn.cpska.ui.splash

import cz.dmn.cpska.di.ActivityScope
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.ui.home.HomeNavigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScope
class SplashPresenter @Inject constructor(private val homeNavigator: HomeNavigator) : BaseMvpPresenter<SplashMvp.View>(), SplashMvp.Presenter {

    private var disposable: Disposable? = null

    override fun attachView(view: SplashMvp.View) {
        super.attachView(view)
        disposable = Observable.timer(2000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            homeNavigator.openHome()
            view.close()
        }
    }

    override fun detachView() {
        super.detachView()
        disposable?.dispose()
    }
}