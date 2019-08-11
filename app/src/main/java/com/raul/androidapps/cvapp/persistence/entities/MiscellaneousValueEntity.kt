package com.raul.androidapps.cvapp.persistence.entities

import androidx.room.*


@Entity(
    foreignKeys = [ForeignKey(
        entity = MiscellaneousEntity::class,
        parentColumns = arrayOf("miscellaneous_id"),
        childColumns = arrayOf("parent_id"),
        onDelete = ForeignKey.CASCADE)],
    tableName = "miscellaneous_value_table",
    indices = [(Index(value = arrayOf("parent_id")))]
)
data class MiscellaneousValueEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "miscellaneous_value_id")
    val miscellaneousValueId: String,
    @ColumnInfo(name = "parent_id")
    val parentId: String,
    @ColumnInfo(name = "position")
    var position: Int,
    @ColumnInfo(name = "value")
    var value: String
) {


    companion object {

        fun fromMiscellaneous(
            value: String,
            parentId: String,
            position: Int
        ): MiscellaneousValueEntity =
            MiscellaneousValueEntity(
                miscellaneousValueId = getMiscellaneousValueId(parentId, position),
                parentId = parentId,
                position = position,
                value = value
            )

        fun getMiscellaneousValueId(parentId: String, position: Int): String =
            "${parentId}_$position"

    }
}

