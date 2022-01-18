package com.example.mindteckdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDAO {
    @Insert
    suspend fun insertCategory(category: Category) : Long

    @Query("SELECT * FROM cat_data_table")
    fun getAllCategories():LiveData<List<Category>>
}