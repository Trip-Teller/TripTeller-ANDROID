package com.our.tripteller.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.our.tripteller.R

class HomeViewHolder(itemView: View, val itemClick: (View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var image : ImageView = itemView.findViewById(R.id.iv_mainimg)
    var region : TextView = itemView.findViewById(R.id.tv_region)
    var intro : TextView = itemView.findViewById(R.id.tv_intro)
    var tag : TextView = itemView.findViewById(R.id.tv_tag1)

    fun bind(myData: HomeData){
        Glide.with(itemView).load(myData.mainimg).into(image)
        region.text = myData.region
        intro.text = myData.intro
        tag.text = myData.tag
        itemView.setOnClickListener{itemClick(itemView)}
    }
}