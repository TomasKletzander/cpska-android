package cz.dmn.cpska.ui.competitions

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.LoadCompetitionsInteractor
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import javax.inject.Inject

@PerActivity
class CompetitionsPresenter @Inject constructor(private val interactor: LoadCompetitionsInteractor)
    : BaseMvpPresenter<CompetitionsMvp.View>(), CompetitionsMvp.Presenter {

    override fun attachView(view: CompetitionsMvp.View) {
        super.attachView(view)
        load()
    }

    override fun detachView() {
        interactor.unsubscribe()
        super.detachView()
    }

    override fun load() {
        interactor.execute(object : BaseInteractorSubscriber<List<Competition>>() {

            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(competitions: List<Competition>) {
                view?.apply {
                    loading = false
                    show(competitions)
                }
            }

            override fun onError(e: Throwable) {
                view?.apply {
                    loading = false
                    error("To be updated")
                }
            }
        })
    }
}