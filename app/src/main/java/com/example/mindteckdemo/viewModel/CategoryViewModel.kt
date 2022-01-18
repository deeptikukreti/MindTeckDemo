package com.example.mindteckdemo.viewModel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mindteckdemo.db.Category
import com.example.mindteckdemo.db.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(var repository: CategoryRepository) : ViewModel(), Observable {
    val catList = repository.getAllCategories
    var isFirstTime=true
    /********add or create dummy  product very first time****/
    fun insertFirstTime(category: Category) = viewModelScope.launch {
        val newRowId = repository.insert(category)

    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}