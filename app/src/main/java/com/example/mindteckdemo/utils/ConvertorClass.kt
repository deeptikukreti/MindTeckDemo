package com.example.mindteckdemo.utils

import com.example.mindteckdemo.viewModel.SubCatBean
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ConvertorClass{
    companion object{
        fun fromStringToArrayList(value: String?): ArrayList<SubCatBean> {
            val listType: Type = object : TypeToken<ArrayList<SubCatBean?>?>() {}.getType()
            return Gson().fromJson(value, listType)
        }

        fun fromArrayListToString(list: ArrayList<SubCatBean>): String {
            val gson = Gson()
            return gson.toJson(list)
        }

    }
}