package com.our.tripteller.ui.home

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.our.tripteller.R
import com.our.tripteller.data.RegionData

class RegionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mainImg : ImageView = itemView.findViewById(R.id.circleImageView)

    fun bind(myData: RegionData){
        mainImg.setImageResource(myData.img)
    }
}