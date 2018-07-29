package cz.dmn.cpska.ui.competition

import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.data.api.CompetitionDetails
import cz.dmn.cpska.data.interactors.BaseInteractorSubscriber
import cz.dmn.cpska.data.interactors.CompetitionDetailsInteractor
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpPresenter
import cz.dmn.cpska.mvp.EmptyPresenterState
import cz.dmn.cpska.mvp.PresenterState
import javax.inject.Inject

@PerActivity
class CompetitionPresenter @Inject constructor(
    private val competition: Competition,
    private val interactor: CompetitionDetailsInteractor,
    override val state: EmptyPresenterState
) : BaseMvpPresenter<CompetitionMvp.View, PresenterState<*>>(), CompetitionMvp.Presenter {

    override fun load() {
        interactor.competitionId = competition.id
        interactor.execute(object : BaseInteractorSubscriber<CompetitionDetails>() {

            override fun onStart() {
                view?.loading = true
            }

            override fun onNext(t: CompetitionDetails) {
                view?.apply {
                    loading = false
                    show(t)
                }
            }

            override fun onError(e: Throwable) {
                view?.apply {
                    loading = false
                    error(e.localizedMessage)
                }
            }
        })
    }

    override fun attachView(view: CompetitionMvp.View) {
        super.attachView(view)
        load()
    }

    override fun detachView() {
        interactor.unsubscribe()
        super.detachView()
    }
}