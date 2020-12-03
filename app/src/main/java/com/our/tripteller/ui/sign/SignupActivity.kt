package com.our.tripteller.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        var id = false
        var pwd1 = false
        var pwd2 = false
        var same = false

        act_signup_btn.setOnClickListener {

            id = if (act_signup_edit_id.text.isNullOrBlank()) {
                act_signup_edit_id.setBackgroundResource(R.drawable.raspberry_stroke_24)
                false
            } else {
                act_signup_edit_id.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                true
            }

            pwd1 =
                if (act_signup_edit_pwd1.text.isNullOrBlank() || act_signup_edit_pwd1.text.length < 5) {
                    act_signup_edit_pwd1.setBackgroundResource(R.drawable.raspberry_stroke_24)
                    act_signup_text_pwdLengh.visibility = View.VISIBLE
                    false
                } else {
                    act_signup_edit_pwd1.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                    act_signup_text_pwdLengh.visibility = View.GONE
                    true
                }

            pwd2 = if (act_signup_edit_pwd2.text.isNullOrBlank()) {
                act_signup_edit_pwd2.setBackgroundResource(R.drawable.raspberry_stroke_24)
                false
            } else {
                act_signup_edit_pwd2.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                true
            }

            same =
                if (!act_signup_edit_pwd2.text.isNullOrBlank() && act_signup_edit_pwd1.text.toString() == act_signup_edit_pwd2.text.toString()) {
                    act_signup_edit_pwd2.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                    true
                } else {
                    act_signup_edit_pwd2.setBackgroundResource(R.drawable.raspberry_stroke_24)
                    false
                }

            if (id && pwd1 && pwd2 && same) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }

}

