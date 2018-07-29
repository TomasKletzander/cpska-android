package cz.dmn.cpska.mvp

import cz.dmn.cpska.di.PerActivity
import org.parceler.Parcel
import javax.inject.Inject

@PerActivity
class EmptyPresenterState @Inject constructor() : PresenterState<EmptyPresenterState.StateHolder>() {

    @Parcel
    class StateHolder

    override fun newStateHolder() = StateHolder()
}