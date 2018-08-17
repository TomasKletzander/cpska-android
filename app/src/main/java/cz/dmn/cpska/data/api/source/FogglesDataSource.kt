package cz.dmn.cpska.data.api.source

import io.reactivex.Observable

interface FogglesDataSource {

    fun getFoggles(): Observable<Map<String, Boolean>>
}