package cz.dmn.cpska.ui.leaderboard

import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.data.api.leaderboard.LeaderboardRow
import cz.dmn.cpska.data.api.User
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@PerActivity
class LeaderboardPresenter @Inject constructor(
    override val state: EmptyPresenterState
) : BaseMvpPresenter<LeaderboardMvp.View, PresenterState<*>>(), LeaderboardMvp.Presenter {

    private var disposables = CompositeDisposable()

    override fun load() {
        view?.apply {
            //  TODO: Replace with real API call
            val rows = mutableListOf<LeaderboardRow>()
            rows.add(LeaderboardRow(1, 1000, User(3080, "Tomas Kletzander"), Club(0, "Medlanky")))
            rows.add(LeaderboardRow(2, 999, User(1, "Franta Kocourek"), Club(0, "Krizanov")))
            rows.add(LeaderboardRow(3, 998, User(2, "Jan Novak"), Club(0, "Pribyslav")))
            rows.add(LeaderboardRow(4, 997, User(3, "Adam Jandora"), Club(0, "Medlanky")))
            disposables.add(Observable.just(rows).delay(2000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
                show(it)
            })
        }
    }

    override fun attachView(view: LeaderboardMvp.View) {
        super.attachView(view)
        load()
    }

    override fun detachView() {
        disposables.clear()
        super.detachView()
    }
}