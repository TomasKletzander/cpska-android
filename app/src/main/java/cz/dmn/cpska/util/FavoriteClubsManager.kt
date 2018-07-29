package cz.dmn.cpska.util

import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.db.FavoriteClubs
import cz.dmn.cpska.db.FavoriteClubsDao
import cz.dmn.cpska.di.PerApplication
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

@PerApplication
class FavoriteClubsManager @Inject constructor(
    private val dao: FavoriteClubsDao,
    private val clubsCache: MemoryCache<List<Club>>
) {

    private val favorites = mutableListOf<FavoriteClubs>()

    val events: Subject<Event> = PublishSubject.create()

    fun initialize(): Observable<Boolean> = Observable.fromCallable {
        dao.getAll()
    }.doOnNext {
        favorites.addAll(it)
        events.onNext(Event.Initialized)
    }.map {
        true
    }

    fun isFavorite(clubId: Int) = favorites.find { it.clubId == clubId } != null

    fun isFavorite(clubName: String) = clubsCache.data.find { it.name == clubName }?.let { isFavorite(it.id) } ?: false

    fun addFavorite(clubId: Int) {
        if (!isFavorite(clubId)) {
            val newFavorite = FavoriteClubs(clubId)
            favorites.add(newFavorite)
            events.onNext(Event.Added)
            Completable.fromAction { dao.insert(newFavorite) }.subscribeOn(Schedulers.io()).subscribe()
        }
    }

    fun removeFavorite(clubId: Int) {
        favorites.find { it.clubId == clubId }?.let {
            favorites.remove(it)
            events.onNext(Event.Removed)
            Completable.fromAction { dao.delete(it) }.subscribeOn(Schedulers.io()).subscribe()
        }
    }

    enum class Event {
        Initialized,
        Added,
        Removed
    }
}