package cz.dmn.cpska.ui.competitions

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.MemoryCache
import cz.dmn.cpska.data.api.Competition
import cz.dmn.cpska.mvp.EmptyPresenterState
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompetitionsPresenterTest {

    lateinit var presenter: CompetitionsPresenter
    @Mock lateinit var view: CompetitionsMvp.View
    @Mock lateinit var competitionsCache: MemoryCache<List<Competition>>
    val data = emptyList<Competition>()

    @Before
    fun setUp() {
        whenever(competitionsCache.data).thenReturn(data)
        presenter = CompetitionsPresenter(competitionsCache, EmptyPresenterState())
    }

    @Test
    fun load() {
        presenter.attachView(view)
        verify(view).show(data)
        verifyNoMoreInteractions(view)
    }
}