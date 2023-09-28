package com.github.hehecoi222.week5slidesimplstoredata.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "simple_table"
)
class SimpleEntity(
    @ColumnInfo(name = "key") val key: String, @ColumnInfo(name = "value") val value: String
)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}