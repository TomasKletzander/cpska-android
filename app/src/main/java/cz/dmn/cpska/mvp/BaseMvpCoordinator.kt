package cz.dmn.cpska.mvp

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import com.squareup.coordinators.Coordinator
import dagger.Lazy
import javax.inject.Inject

abstract class BaseMvpCoordinator<V: MvpView, P: MvpPresenter<V>, out VB: ViewDataBinding> : Coordinator() {

    @Inject lateinit var presenterLazy: Lazy<P>
    @Inject lateinit var viewLazy: Lazy<V>
    val presenter by lazy {
        presenterLazy.get()
    }
    val view by lazy { viewLazy.get() }
    private lateinit var _binding: VB
    val binding: VB
        get() = _binding

    override fun attach(view: View) {
        _binding = DataBindingUtil.getBinding(view)!!
        onAttach()
        presenter.attachView(this.view)
    }

    override fun detach(view: View) {
        presenter.detachView()
    }

    abstract fun onAttach()
}