package com.raul.androidapps.cvapp.persistence

import com.raul.androidapps.cvapp.persistence.entities.FooEntity

interface PersistenceManager {

    suspend fun getFoo(name: String): List<FooEntity>

}