package cz.dmn.cpska.data.api.source

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import cz.dmn.cpska.data.api.DmnApi
import cz.dmn.cpska.data.api.cfg.Configuration
import cz.dmn.cpska.data.api.cfg.Foggles
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DmnDataSourceTest {

    lateinit var dataSource: DmnDataSource
    @Mock lateinit var cfg: Configuration
    @Mock lateinit var foggles: Foggles
    @Mock lateinit var api: DmnApi

    @Before
    fun setUp() {
        whenever(cfg.foggles).thenReturn(foggles)
        whenever(foggles.url).thenReturn("fogglesUrl")
        dataSource = DmnDataSource(cfg, api)
    }

    @Test
    fun getFoggles() {
        dataSource.getFoggles()
        verify(api).getFoggles("fogglesUrl")
    }
}