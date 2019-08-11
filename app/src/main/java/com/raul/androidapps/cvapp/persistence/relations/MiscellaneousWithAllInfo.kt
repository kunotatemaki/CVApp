package com.raul.androidapps.cvapp.persistence.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.raul.androidapps.cvapp.persistence.entities.MiscellaneousEntity
import com.raul.androidapps.cvapp.persistence.entities.MiscellaneousValueEntity


class MiscellaneousWithAllInfo {
    @Embedded
    lateinit var miscellaneous: MiscellaneousEntity

    @Relation(parentColumn = "miscellaneous_id", entityColumn = "parent_id")
    lateinit var values: List<MiscellaneousValueEntity>

}