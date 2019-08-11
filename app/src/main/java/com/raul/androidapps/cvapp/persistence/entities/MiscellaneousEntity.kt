package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.raul.androidapps.cvapp.model.Miscellaneous


@Entity(
    tableName = "miscellaneous_table",
    indices = [(Index(value = arrayOf("miscellaneous_id"), unique = true))]
)
data class MiscellaneousEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "miscellaneous_id")
    val miscellaneousId: String,
    @ColumnInfo(name = "gist_id")
    var gistId: String,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "title")
    var title: String
) {

    companion object {

        fun fromMiscellaneous(
            miscellaneous: Miscellaneous,
            gistId: String,
            position: Int
        ): MiscellaneousEntity =
            MiscellaneousEntity(
                miscellaneousId = getMiscellaneusId(gistId, miscellaneous.id),
                gistId = gistId,
                title = miscellaneous.title,
                position = position
            )

        fun getMiscellaneusId(gistId: String, id: Int): String =
            "${gistId}_$id"

    }
}

