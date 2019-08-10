package com.raul.androidapps.cvapp.persistence

import com.raul.androidapps.softwaretesttandem.persistence.databases.CVAppDatabase
import com.raul.androidapps.cvapp.persistence.entities.FooEntity
import javax.inject.Inject

class PersistenceManagerImpl @Inject constructor(
    private val db: CVAppDatabase
) : PersistenceManager {

    override suspend fun getFoo(name: String): List<FooEntity> =
        db.fooDao().getFoo()

}

