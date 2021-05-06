package com.our.tripteller.ui.home.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.our.tripteller.R
import com.our.tripteller.ui.chat.chatroom.ChatRoomActivity
import kotlinx.android.synthetic.main.activity_request_last.*

class RequestLastActivity : AppCompatActivity() {
    private var goal = false
    private var want = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_last)

        et_goal.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    tv_goal_cnt.text = "0/150"
                    btn_apply.isEnabled = false
                    goal = false
                } else {
                    tv_goal_cnt.text = et_goal.text.length.toString() + "/150"
                    goal = true
                    if(goal && want){
                        btn_apply.isEnabled = true
                    }
                }
            }
        })

        et_want.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    tv_want_cnt.text = "0/250"
                    btn_apply.isEnabled = false
                    want = false
                } else {
                    tv_want_cnt.text = et_want.text.length.toString() + "/250"
                    want = true
                    if(goal && want){
                        btn_apply.isEnabled = true
                    }
                }
            }
        })

        btn_apply.setOnClickListener {
            startActivity(Intent(this, ChatRoomActivity::class.java))
            finish()
        }

    }
}