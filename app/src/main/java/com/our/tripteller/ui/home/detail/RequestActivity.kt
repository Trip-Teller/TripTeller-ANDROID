package com.our.tripteller.ui.home.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.CustomDialog
import com.our.tripteller.R
import com.our.tripteller.ui.chat.ChatRoom.ChatRoomActivity
import kotlinx.android.synthetic.main.activity_request.*

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        btn_fin.setOnClickListener {
            val customDialog = CustomDialog(this)
            customDialog.setOnOKClickedListener {
                val intent = Intent(this, ChatRoomActivity::class.java)
                startActivity(intent)
                finish()
            }
            customDialog.start(0,"✈️\n이 내용으로 신청하시겠어요?","네, 맞아요", "아직이요!")
        }
    }
}