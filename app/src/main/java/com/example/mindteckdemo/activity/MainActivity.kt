package com.example.mindteckdemo.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.mindteckdemo.R
import com.example.mindteckdemo.adapter.CategoryAdapter
import com.example.mindteckdemo.adapter.SubCategoryAdapter
import com.example.mindteckdemo.db.Category
import com.example.mindteckdemo.db.CategoryDatabase
import com.example.mindteckdemo.db.CategoryRepository
import com.example.mindteckdemo.utils.ConvertorClass
import com.example.mindteckdemo.viewModel.CategoryViewModel
import com.example.mindteckdemo.viewModel.CategoryViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var catViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var subcatAdapter: SubCategoryAdapter
    lateinit var catList: ArrayList<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = CategoryDatabase.getInstance(application).categoryDAO
        val repository = CategoryRepository(dao)
        val factory = CategoryViewModelFactory(repository)
        catViewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        categoryAdapter = CategoryAdapter()
        subcatAdapter= SubCategoryAdapter()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if(newText.isNullOrEmpty())
                subcatAdapter.getFilter().filter("")
                else
                subcatAdapter.getFilter().filter(newText)
                return false
            }
        })
        view_pager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            // This method is triggered when there is any scrolling activity for the current page

            // triggered when you select a new page
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(this@MainActivity,"$position",Toast.LENGTH_SHORT).show()
                var list=ConvertorClass.fromStringToArrayList(catList[position].subcat_list)
                subcatAdapter.setList(list)
                rvSubCat.adapter=subcatAdapter
                var subcatList=ConvertorClass.fromStringToArrayList(catList[position].subcat_list)
                subcatAdapter.setList(subcatList)
                rvSubCat.adapter=subcatAdapter
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getcategoryList()
    }
    private fun getcategoryList() {
        catViewModel.catList.observe(
            this,
            Observer<List<Category?>?> {
                if(it!=null&&it.size>0) {
                    catList = it as ArrayList<Category>
                    categoryAdapter.setList(catList)
                    view_pager2.adapter = categoryAdapter
                    spring_dots_indicator.setViewPager2(view_pager2)
                }else{
                    mainLayout.visibility=View.GONE
                    txtNoData.visibility=View.VISIBLE
                }
            })
    }
}

