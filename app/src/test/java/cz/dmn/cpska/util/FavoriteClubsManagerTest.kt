package cz.dmn.cpska.util

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.RxSchedulersOverrideRule
import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Club
import cz.dmn.cpska.db.FavoriteClubs
import cz.dmn.cpska.db.FavoriteClubsDao
import cz.dmn.cpska.testutil.shouldEqual
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteClubsManagerTest {

    lateinit var favoriteClubsManager: FavoriteClubsManager
    @Mock lateinit var dao: FavoriteClubsDao
    @Mock lateinit var clubsCache: MemoryCache<List<Club>>
    val testObserver = TestObserver.create<FavoriteClubsManager.Event>()
    @Rule @JvmField val rxRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        favoriteClubsManager = FavoriteClubsManager(dao, clubsCache)
    }

    @Test
    fun testInitialize() {
        whenever(dao.getAll()).thenReturn(listOf(FavoriteClubs(1), FavoriteClubs(2)))
        favoriteClubsManager.events.subscribe(testObserver)
        favoriteClubsManager.initialize().subscribe()
        verify(dao).getAll()
        verifyNoMoreInteractions(dao)
        testObserver.assertNoErrors()
        testObserver.assertValue(FavoriteClubsManager.Event.Initialized)
        favoriteClubsManager.isFavorite(1) shouldEqual true
        favoriteClubsManager.isFavorite(2) shouldEqual true
        favoriteClubsManager.isFavorite(3) shouldEqual false
    }

    @Test
    fun testAddedFavorite() {
        favoriteClubsManager.events.subscribe(testObserver)
        favoriteClubsManager.isFavorite(1) shouldEqual false
        favoriteClubsManager.addFavorite(1)
        verify(dao).insert(any())
        verifyNoMoreInteractions(dao)
        testObserver.assertNoErrors()
        testObserver.assertValue(FavoriteClubsManager.Event.Added)
        favoriteClubsManager.isFavorite(1) shouldEqual true
        favoriteClubsManager.removeFavorite(1)
        verify(dao).delete(any())
        verifyNoMoreInteractions(dao)
        testObserver.assertNoErrors()
        testObserver.assertValues(FavoriteClubsManager.Event.Added, FavoriteClubsManager.Event.Removed)
        favoriteClubsManager.isFavorite(1) shouldEqual false
    }
}