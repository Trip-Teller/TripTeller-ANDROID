package com.our.tripteller.ui.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.our.tripteller.R
import com.our.tripteller.data.ChatListData

class ChatListAdapter(private val context : Context, val itemClick: (View) -> Unit) : RecyclerView.Adapter<ChatListViewHolder>() {
    var datas = mutableListOf<ChatListData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat_list,parent,false)
        return ChatListViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}

class ChatListViewHolder(itemView: View, val itemClick: (View) -> Unit) : RecyclerView.ViewHolder(itemView) {

    val profileImg: ImageView = itemView.findViewById(R.id.civ_profile)
    val nickname: TextView = itemView.findViewById(R.id.tv_nickname)
    val preview: TextView = itemView.findViewById(R.id.tv_preview)
    val time: TextView = itemView.findViewById(R.id.tv_time)
    val count: TextView = itemView.findViewById(R.id.tv_count)

    fun bind(myData: ChatListData){
        Glide.with(itemView).load(myData.profileImg).into(profileImg)
        nickname.text = myData.nickname
        preview.text = myData.preview
        time.text = myData.time
        count.text = myData.count
        itemView.setOnClickListener{itemClick(itemView)}
    }
}