package com.our.tripteller.ui.chat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.ChatListData
import com.our.tripteller.ui.chat.ChatRoom.ChatRoomActivity
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {

    lateinit var chatListAdapter: ChatListAdapter
    val chatListData = mutableListOf<ChatListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatListAdapter = ChatListAdapter(view.context) { view: View ->
            var intent = Intent(activity as MainActivity, ChatRoomActivity::class.java)
            startActivity(intent)
        }
        rv_chat_list.adapter = chatListAdapter
        loadChatListDatas()
    }

    private fun loadChatListDatas() {
        chatListData.apply {
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요 궁금한게 있어요 궁금한게 있어요 궁금한게 있어요 궁금한게 있어요 궁금한게 있어요 궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
            add(ChatListData(profileImg = "", nickname = "홍옌옌", preview = "궁금한게 있어요", time = "오후 8:50", count = "99"))
        }
        chatListAdapter.datas = chatListData
        chatListAdapter.notifyDataSetChanged()
    }
}