package com.example.mindteckdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cat_data_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "cat_name")
    var cat_name: String,
    @ColumnInfo(name = "cat_image")
    var cat_image: String,
    @ColumnInfo(name = "subcat_list")
    var subcat_list: String

)

