package com.our.tripteller.ui.home.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.our.tripteller.R

class DetailHashtagAdapter (private val context: Context): RecyclerView.Adapter<DetailHashtagViewHolder>(){

    data class HashtagData(
        val hashtagData: String
    )

    var datas = arrayListOf<HashtagData>(
        HashtagData("효도여행"),
        HashtagData("맛집투어"),
        HashtagData("힐링")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHashtagViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_detail_hashtag, parent, false)
        return DetailHashtagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: DetailHashtagViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}


class DetailHashtagViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
    val item_detail_hashtag = itemview.findViewById<TextView>(R.id.item_detail_hashtag)

    fun bind(datas: DetailHashtagAdapter.HashtagData) {
        item_detail_hashtag.text = datas.hashtagData
    }
}