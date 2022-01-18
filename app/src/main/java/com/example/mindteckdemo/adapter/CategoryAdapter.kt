package com.example.mindteckdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mindteckdemo.R
import com.example.mindteckdemo.db.Category
import kotlinx.android.synthetic.main.view_pager_item.view.*

class CategoryAdapter : RecyclerView.Adapter<ViewHolder>() {
  lateinit var catList: ArrayList<Category>
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return object : ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)) {}
  }

  override fun getItemCount(): Int {
    if(catList!=null && catList.size>0)
    return catList.size
    else
      return 0
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.itemView.tvCatName.text=catList[position].cat_name
  }
  fun setList(it: ArrayList<Category>) {
    if(it!=null&&it.size>0){
      catList=it
      notifyDataSetChanged()
    }

  }
}
