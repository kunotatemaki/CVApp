package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.raul.androidapps.cvapp.model.Expertise


@Entity(
    tableName = "company_table", primaryKeys = ["gist_id", "company_id"],
    indices = [(Index(value = arrayOf("gist_id")))]
)
data class CompanyEntity constructor(
    @ColumnInfo(name = "gist_id")
    val gistId: String,
    @ColumnInfo(name = "company_id")
    var companyId: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "date")
    var date: String
) {

    companion object {

        fun fromExpertise(expertise: Expertise, gistId: String): CompanyEntity =
            CompanyEntity(
                gistId = gistId,
                companyId = expertise.id,
                name = expertise.company,
                date = expertise.date
            )

    }
}

