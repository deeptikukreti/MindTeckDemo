package com.example.mindteckdemo.activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mindteckdemo.R
import com.example.mindteckdemo.db.Category
import com.example.mindteckdemo.db.CategoryDatabase
import com.example.mindteckdemo.db.CategoryRepository
import com.example.mindteckdemo.utils.ConvertorClass
import com.example.mindteckdemo.utils.SharedPreferenceUtil
import com.example.mindteckdemo.viewModel.CategoryViewModel
import com.example.mindteckdemo.viewModel.CategoryViewModelFactory
import com.example.mindteckdemo.viewModel.SubCatBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
/*add this new line*/
class SplashActivity : AppCompatActivity() {
    private lateinit var catViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dao = CategoryDatabase.getInstance(application).categoryDAO
        val repository = CategoryRepository(dao)
        val factory = CategoryViewModelFactory(repository)
        catViewModel = ViewModelProvider(this, factory).get(CategoryViewModel::class.java)
        if (SharedPreferenceUtil.getInstance(this).isFirstTimeInstall!!) {
            setDummyData()
        }
        Handler().postDelayed({
          startActivity(Intent(this,MainActivity::class.java))
          finishAffinity()
        },3000)
    }

    private fun setDummyData() {
        SharedPreferenceUtil.getInstance(this).isFirstTimeInstall = false
        var subCatList1:ArrayList<SubCatBean> = ArrayList()
        var subCatList2:ArrayList<SubCatBean> = ArrayList()
        var subCatList3:ArrayList<SubCatBean> = ArrayList()

        subCatList1.add(SubCatBean(0,"skull Candy","subcat_img"))
        subCatList1.add(SubCatBean(0,"Boat","subcat_img"))
        subCatList1.add(SubCatBean(0,"oppo ear buds","subcat_img"))
        val subCat1= ConvertorClass.fromArrayListToString(subCatList1)
       GlobalScope.launch {  addDummyData("Ear Phones",catViewModel,subCat1)}

        subCatList2.add(SubCatBean(0,"Hp","subcat_img"))
        subCatList2.add(SubCatBean(0,"Dell","subcat_img"))
        subCatList2.add(SubCatBean(0,"Lenovo","subcat_img"))
        subCatList2.add(SubCatBean(0,"Mac","subcat_img"))
        val subCat2= ConvertorClass.fromArrayListToString(subCatList2)
        GlobalScope.launch {  addDummyData("Laptops",catViewModel,subCat2)}

        subCatList3.add(SubCatBean(0,"Samsung","subcat_img"))
        subCatList3.add(SubCatBean(0,"I-Phone","subcat_img"))
        subCatList3.add(SubCatBean(0,"One+","subcat_img"))
        subCatList3.add(SubCatBean(0,"RedMi","subcat_img"))
        subCatList3.add(SubCatBean(0,"Nokia","subcat_img"))
        val subCat3= ConvertorClass.fromArrayListToString(subCatList3)
        GlobalScope.launch {  addDummyData("Mobile Phones",catViewModel,subCat3)}

    }

    fun addDummyData(
        catName:String,
         catViewModel: CategoryViewModel,
         subCatList: String
    ) {
        var catViewModel:CategoryViewModel=catViewModel
        catViewModel.insertFirstTime(Category(0,catName,"cat_image",subCatList))
    }

}