package com.our.tripteller.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.our.tripteller.R

class HomeAdapter(private val context : Context, val itemClick: (View) -> Unit) : RecyclerView.Adapter<HomeViewHolder>() {
    var datas = mutableListOf<HomeData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false)
        return HomeViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}