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

class HomeAdapter(private val context : Context, val itemClick: (HomeData, View) -> Unit) : RecyclerView.Adapter<HomeViewHolder>() {
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

class HomeViewHolder(itemView: View, val itemClick: (HomeData, View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var image : ImageView = itemView.findViewById(R.id.iv_mainimg)
    var region : TextView = itemView.findViewById(R.id.tv_region)
    var intro1 : TextView = itemView.findViewById(R.id.tv_intro1)
    var intro2 : TextView = itemView.findViewById(R.id.tv_intro2)
    var tag1 : TextView = itemView.findViewById(R.id.tv_tag1)
    var tag2 : TextView = itemView.findViewById(R.id.tv_tag2)
    var tag3 : TextView = itemView.findViewById(R.id.tv_tag3)
    var tagTemp : TextView = itemView.findViewById(R.id.tv_tag_temp)
    var tag4 : TextView = itemView.findViewById(R.id.tv_tag4)
    var tag5 : TextView = itemView.findViewById(R.id.tv_tag5)
    var tag6 : TextView = itemView.findViewById(R.id.tv_tag6)

    fun bind(myData: HomeData){
        Glide.with(itemView).load(myData.mainimg).into(image)
        region.text = myData.region
        var top = myData.intro1.length
        var bottom = myData.intro2.length
        var cnt = myData.cnt

        if(top <= bottom){
            intro1.setBackgroundResource(R.drawable.home_intro_1_2)
            intro2.setBackgroundResource(R.drawable.home_intro_2_2)
        }
        intro1.text = myData.intro1
        intro2.text = myData.intro2
        if(myData.tag1 == null)
            tag1.visibility = View.GONE
        else
            tag1.text = "#" + myData.tag1
        if(myData.tag2 == null)
            tag2.visibility = View.GONE
        else
            tag2.text = "#" +myData.tag2
        if(myData.tag3 == null)
            tag3.visibility = View.GONE
        else
            tag3.text = "#" +myData.tag3
        if(cnt == 4){
            tagTemp.text = "#" +myData.tag4
            tagTemp.visibility = View.VISIBLE
            tag4.visibility = View.GONE
        }
        else {
            if (myData.tag4 == null)
                tag4.visibility = View.GONE
            else
                tag4.text = "#" + myData.tag4
        }
        if(myData.tag5 == null)
            tag5.visibility = View.GONE
        else
            tag5.text = "#" +myData.tag5
        if(myData.tag6 == null)
            tag6.visibility = View.GONE
        else
            tag6.text = "#" +myData.tag6
        itemView.setOnClickListener{itemClick(myData, itemView)}
    }
}