package com.example.mindteckdemo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mindteckdemo.db.CategoryRepository
import java.lang.IllegalArgumentException

class CategoryViewModelFactory(private val repository: CategoryRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }

}