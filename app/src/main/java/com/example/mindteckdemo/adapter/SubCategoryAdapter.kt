package com.example.mindteckdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mindteckdemo.R
import com.example.mindteckdemo.viewModel.SubCatBean
import kotlinx.android.synthetic.main.subcat_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class SubCategoryAdapter : RecyclerView.Adapter<ViewHolder>() {
  lateinit var mainsubcatList: ArrayList<SubCatBean>
  lateinit var subcatList: ArrayList<SubCatBean>
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return object : ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subcat_item, parent, false)) {}
  }

  override fun getItemCount(): Int {
    if(subcatList!=null && subcatList.size>0)
      return subcatList.size
    else
      return 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemView.tvSubCatName.text=subcatList[position].subcat_name
  }
  fun setList(it: ArrayList<SubCatBean>) {
    if(it!=null&&it.size>0){
      mainsubcatList=it
      subcatList=it
      notifyDataSetChanged()
    }
  }
  //FILTER
  fun getFilter(): Filter {
    return object : Filter() {
      override fun performFiltering(charSequence: CharSequence): FilterResults {
        val charString = charSequence.toString()
        if (charString.isEmpty()) {
          subcatList = mainsubcatList
        } else {
          val filteredList = mutableListOf<SubCatBean>()
          for (index in mainsubcatList!!.indices) {
            if (mainsubcatList!!.get(index)!!.subcat_name!!.toString()
                .lowercase(Locale.getDefault())
                .contains(charString.lowercase(Locale.getDefault()))) {
              filteredList.add(mainsubcatList!!.get(index)!!)
            }
          }
          subcatList = filteredList as ArrayList<SubCatBean>
        }
        val filterResults = FilterResults()
        filterResults.values = subcatList
        return filterResults
      }

      override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
        subcatList = (filterResults.values as? ArrayList<SubCatBean>)!!
        notifyDataSetChanged()
      }
    }
  }
}
