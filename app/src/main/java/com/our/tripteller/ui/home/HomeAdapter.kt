package com.our.tripteller.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.our.tripteller.R
import com.our.tripteller.data.HomeData
import com.our.tripteller.ui.home.Detail.DetailHashtagAdapter

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

class HomeViewHolder(itemView: View, val itemClick: (View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var image : ImageView = itemView.findViewById(R.id.iv_mainimg)
    var region : TextView = itemView.findViewById(R.id.tv_region)
    var intro : TextView = itemView.findViewById(R.id.tv_intro)
    var tag1 : TextView = itemView.findViewById(R.id.tv_tag1)
    var tag2 : TextView = itemView.findViewById(R.id.tv_tag2)
    var tag3 : TextView = itemView.findViewById(R.id.tv_tag3)
    var tag4 : TextView = itemView.findViewById(R.id.tv_tag4)
    var tag5 : TextView = itemView.findViewById(R.id.tv_tag5)
    var tag6 : TextView = itemView.findViewById(R.id.tv_tag6)

    fun bind(myData: HomeData){
        Glide.with(itemView).load(myData.mainimg).into(image)
        region.text = myData.region
        intro.text = myData.intro
        tag1.text = myData.tag
        tag4.visibility = View.GONE
        tag5.visibility = View.GONE
        tag6.visibility = View.GONE
        itemView.setOnClickListener{itemClick(itemView)}
    }
}