package cz.dmn.cpska.mvp

import android.os.Parcelable
import org.parceler.Parcels

abstract class PresenterState<SH> {
    protected var stateHolder: SH = newStateHolder()
    abstract fun newStateHolder(): SH

    fun save(): Parcelable = Parcels.wrap(stateHolder)

    fun restore(parcelable: Parcelable) {
        stateHolder = Parcels.unwrap(parcelable)
    }
}