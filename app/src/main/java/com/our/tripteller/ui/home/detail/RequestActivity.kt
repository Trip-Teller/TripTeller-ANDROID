package com.our.tripteller.ui.home.detail

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.our.tripteller.CustomDialog
import com.our.tripteller.R
import com.our.tripteller.ui.chat.chatroom.ChatRoomActivity
import kotlinx.android.synthetic.main.activity_request.*
import kotlinx.android.synthetic.main.activity_request.tv_nickname
import java.util.*

class RequestActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        if (intent.hasExtra("profile")) {
            val profile =  intent.getStringExtra("profile")
            Glide.with(this).load(profile).into(civ_profile)
            tv_nickname.text = intent.getStringExtra("nickname")
            tv_age.text = intent.getStringExtra("age")
            tv_gender.text = intent.getStringExtra("gender")

        }

        btn_fin.setOnClickListener {
            val customDialog = CustomDialog(this)
            customDialog.setOnOKClickedListener {
                val intent = Intent(this, ChatRoomActivity::class.java)
                intent.putExtra("nickname",tv_nickname.text)
                startActivity(intent)
                finish()
            }
            customDialog.start("✈️\n이 내용으로 신청하시겠어요?","네, 맞아요", "아직이요!")
        }

        tv_start.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, year, month, day ->
                tv_start.text = (month+1).toString() + "월 " + day.toString() + "일"
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }

        tv_end.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, year, month, day ->
                tv_end.text = (month+1).toString() + "월" + day.toString() + "일"
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }
    }
}