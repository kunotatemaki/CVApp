package com.raul.androidapps.cvapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.raul.androidapps.cvapp.persistence.entities.CompanyEntity
import com.raul.androidapps.cvapp.persistence.relations.CompanyWithAllInfo


@Dao
abstract class CompanyDao : BaseDao<CompanyEntity>() {

    @Query("SELECT * FROM company_table WHERE gist_id LIKE :gistId ORDER BY company_id DESC")
    abstract fun getListOfCompany(gistId: String): LiveData<List<CompanyWithAllInfo>>

}