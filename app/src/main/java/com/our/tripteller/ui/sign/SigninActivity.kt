package com.our.tripteller.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.IMAGES_RESOURCE
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        act_signin_btn_check.isSelected = false
        val datas = IMAGES_RESOURCE

        act_signin_kenBurnsView.setImageResource(datas[intent.getIntExtra("img", 1)])

        act_signin_btn_check.setOnClickListener {
            act_signin_btn_check.isSelected = !act_signin_btn_check.isSelected
        }

        act_signin_btn.setOnClickListener {

            if (act_signin_edit_id.text.isNullOrBlank() || act_signin_edit_pwd.text.isNullOrBlank()) {
                act_signin_text_fail.visibility = View.VISIBLE

                if (act_signin_edit_id.text.isNullOrBlank()){
                    act_signin_edit_id.setBackgroundResource(R.drawable.raspberry_stroke_24)
                }else{
                    act_signin_edit_id.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                }

                if (act_signin_edit_pwd.text.isNullOrBlank()){
                    act_signin_edit_pwd.setBackgroundResource(R.drawable.raspberry_stroke_24)
                }else{
                    act_signin_edit_pwd.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                }

            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}