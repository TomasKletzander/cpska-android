package cz.dmn.cpska.data

import cz.dmn.cpska.di.PerApplication
import javax.inject.Inject

@PerApplication
class MemoryCache<T: Any> @Inject constructor() {
    lateinit var data: T
}