package com.our.tripteller.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.our.tripteller.R

class RegionAdapter(private val context : Context) : RecyclerView.Adapter<RegionViewHolder>() {
    var datas = mutableListOf<RegionData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_region,parent,false)
        return RegionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}