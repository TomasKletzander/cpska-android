package cz.dmn.cpska.ui.clubs

import android.support.design.widget.Snackbar
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.databinding.CoordClubsBinding
import cz.dmn.cpska.di.PerActivity
import cz.dmn.cpska.mvp.BaseMvpCoordinator
import cz.dmn.cpska.ui.ItemClickListener
import cz.dmn.cpska.ui.common.ItemToggleListener
import dagger.Lazy
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.text.Collator
import java.util.*
import javax.inject.Inject

@PerActivity
class ClubsCoordinator @Inject constructor(private val adapterLazy: Lazy<ClubsAdapter>)
    : BaseMvpCoordinator<ClubsMvp.View, ClubsMvp.Presenter, CoordClubsBinding>(),
        ClubsMvp.View, ItemClickListener<Club>, ItemToggleListener<Club> {

    private val adapter by lazy { adapterLazy.get() }
    override val requestOpenClub: Subject<Club> = PublishSubject.create()
    override val toggleFavoriteClub: Subject<Club> = PublishSubject.create()
    private val collator = Collator.getInstance(Locale("cs"))

    override fun onAttach() {
        binding.adapter = adapter
    }

    override fun show(clubs: List<Pair<Club, Boolean>>) {
        adapter.update(clubs.map {
            ClubViewModel(it.first, it.second)
        }.sortedWith(clubsComparator))
    }

    override fun error(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onItemClicked(item: Club) {
        requestOpenClub.onNext(item)
    }

    override fun onToggleItem(item: Club) {
        toggleFavoriteClub.onNext(item)
    }

    val clubsComparator = Comparator<ClubViewModel> { o1, o2 ->
        if (o1.favorite.get() == o2.favorite.get()) {
            collator.compare(o1.clubName, o2.clubName)
        } else if (o1.favorite.get()) -1 else 1
    }
}