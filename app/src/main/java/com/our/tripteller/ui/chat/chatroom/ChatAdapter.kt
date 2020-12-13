package com.our.tripteller.ui.chat.chatroom


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.our.tripteller.R
import com.our.tripteller.data.MessageData
import com.our.tripteller.data.MessageData.Companion.TYPE_FRIEND_MESSAGE
import com.our.tripteller.data.MessageData.Companion.TYPE_MY_MESSAGE

class ChatAdapter(var data: MutableList<MessageData>) : RecyclerView.Adapter<MessageViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder<*> {
        val context = parent.context
        return when (viewType) {
            TYPE_MY_MESSAGE -> {
                val view = LayoutInflater.from(context).inflate(R.layout.item_my_chat, parent, false)
                MyMessageViewHolder(view)
            }
            TYPE_FRIEND_MESSAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_your_chat, parent, false)
                FriendMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder<*>, position: Int) {
        val item = data[position]
        when (holder) {
            is MyMessageViewHolder -> holder.bind(item)
            is FriendMessageViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].messageType

}


abstract class MessageViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

class MyMessageViewHolder(itemView: View) : MessageViewHolder<MessageData>(itemView) {

    private val chat_text: TextView = itemView.findViewById(R.id.txt_myChat)
    private val chat_time: TextView = itemView.findViewById(R.id.txt_myChatTime)

    override fun bind(item: MessageData) {
        chat_text.text = item.text
        chat_time.text = item.text_time
    }


}
class FriendMessageViewHolder(itemView: View) : MessageViewHolder<MessageData>(itemView) {
    private val text_chat: TextView = itemView.findViewById(R.id.txt_youChat)
    private val text_time: TextView = itemView.findViewById(R.id.txt_youChatTime)

    override fun bind(item: MessageData) {
        text_chat.text = item.text
        text_time.text = item.text_time
    }


}