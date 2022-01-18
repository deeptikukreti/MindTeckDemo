package com.example.mindteckdemo.db


class CategoryRepository(private val dao : CategoryDAO) {

    val getAllCategories = dao.getAllCategories()

    suspend fun insert(category: Category):Long{
       return dao.insertCategory(category)
    }

}