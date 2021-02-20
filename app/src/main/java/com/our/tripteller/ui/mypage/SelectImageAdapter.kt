package com.our.tripteller.ui.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.our.tripteller.R
import com.our.tripteller.data.ImageInfoData

class SelectImageAdapter(private val context : Context, val itemClick: (ImageInfoData, View) -> Unit) : RecyclerView.Adapter<SelectImageViewHolder>() {
    var datas = mutableListOf<ImageInfoData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_unsplash,parent,false)
        return SelectImageViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: SelectImageViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}

class SelectImageViewHolder(itemView: View, val itemClick: (ImageInfoData, View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    var image : ImageView = itemView.findViewById(R.id.iv_img)

    fun bind(myData: ImageInfoData){
        Glide.with(itemView).load(myData.urls.thumb).into(image)
        itemView.setOnClickListener{itemClick(myData, itemView)}
    }
}