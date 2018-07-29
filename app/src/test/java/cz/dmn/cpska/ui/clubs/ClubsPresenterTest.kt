package cz.dmn.cpska.ui.clubs

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.navigators.ClubNavigator
import cz.dmn.cpska.testutil.shouldEqual
import cz.dmn.cpska.util.FavoriteClubsManager
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Captor

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClubsPresenterTest {

    lateinit var presenter: ClubsPresenter
    @Mock lateinit var clubsCache: MemoryCache<List<Club>>
    @Mock lateinit var clubNavigator: ClubNavigator
    @Mock lateinit var favoriteClubsManager: FavoriteClubsManager
    @Mock lateinit var view: ClubsMvp.View
    val toggleFavoriteSubject = PublishSubject.create<Club>()
    val requestOpenSubject = PublishSubject.create<Club>()
    val eventsSubject = PublishSubject.create<FavoriteClubsManager.Event>()
    val clubsCacheData = listOf(Club(1, "Medlanky"), Club(2, "Krizanov"))

    @Before
    fun setUp() {
        whenever(view.toggleFavoriteClub).thenReturn(toggleFavoriteSubject)
        whenever(view.requestOpenClub).thenReturn(requestOpenSubject)
        whenever(favoriteClubsManager.events).thenReturn(eventsSubject)
        whenever(clubsCache.data).thenReturn(clubsCacheData)
        presenter = ClubsPresenter(clubsCache, clubNavigator, favoriteClubsManager)
    }

    @Test
    fun attachView() {
        presenter.attachView(view)
        verify(view).toggleFavoriteClub
        verify(view).requestOpenClub
        argumentCaptor<List<Pair<Club, Boolean>>>().let {
            verify(view).show(it.capture())
            verifyNoMoreInteractions(view)
            val showData = it.firstValue
            showData.size shouldEqual 2
            showData[0].first shouldEqual clubsCacheData[0]
        }
    }

    @Test
    fun testRefresh() {
        presenter.attachView(view)
        reset(view)
        eventsSubject.onNext(FavoriteClubsManager.Event.Added)
        verify(view).show(any())
    }

    @Test
    fun testToggleFavoriteClub() {
        whenever(favoriteClubsManager.isFavorite(1)).thenReturn(true)
        presenter.attachView(view)
        reset(view)
        toggleFavoriteSubject.onNext(clubsCacheData[0])
        verify(favoriteClubsManager).removeFavorite(1)
    }

    @Test
    fun testFavoriteFlags() {
        whenever(favoriteClubsManager.isFavorite(anyInt())).thenReturn(false)
        whenever(favoriteClubsManager.isFavorite(1)).thenReturn(true)
        presenter.attachView(view)
        argumentCaptor<List<Pair<Club, Boolean>>>().let {
            verify(view).show(it.capture())
            val showData = it.firstValue
            showData.size shouldEqual 2
            showData[0].first shouldEqual clubsCacheData[0]
            showData[0].second shouldEqual true
            showData[1].first shouldEqual clubsCacheData[1]
            showData[1].second shouldEqual false
        }
    }
}