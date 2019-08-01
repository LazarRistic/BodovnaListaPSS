package com.overswayit.plesnisavezsrbije.viewmodels

import android.app.Application
import com.overswayit.plesnisavezsrbije.models.DanceType

/**
 * Created by lazarristic on 2019-07-31.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class LaListViewModel(application: Application) : ListViewModel2(application) {

    override suspend fun fetchList() {
        listRepository.insertOrUpdate(listRepository.getLatestPointList())
    }

    override suspend fun refreshList() {
        list = listRepository.getPointListCouplesWithQuery(DanceType.LA, query)
        observable.addSource(list, observable::setValue)
    }
}