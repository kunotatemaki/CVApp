package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raul.androidapps.cvapp.model.Expertise


@Entity(
    tableName = "company_table",
    indices = [(Index(value = arrayOf("gist_id")))]
)
data class CompanyEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "company_id")
    var companyId: String,
    @ColumnInfo(name = "gist_id")
    val gistId: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "date")
    var date: String
) {

    companion object {

        fun fromExpertise(expertise: Expertise, gistId: String): CompanyEntity =
            CompanyEntity(
                gistId = gistId,
                companyId = getCompanyId(gistId, expertise.id),
                name = expertise.company,
                date = expertise.date
            )

        fun getCompanyId(gistId: String, companyId: Int): String = "${gistId}_$companyId"

    }
}

