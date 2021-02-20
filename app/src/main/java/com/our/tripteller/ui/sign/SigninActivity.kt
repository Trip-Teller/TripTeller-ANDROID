package com.our.tripteller.ui.sign

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.IMAGES_RESOURCE
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_signin)

        val datas = IMAGES_RESOURCE

        val rnds = intent.getIntExtra("img", 0)
        act_signin_kenBurnsView.setImageResource(datas[rnds])

        act_signin_btn_check.setOnClickListener {
            act_signin_btn_check.isSelected = !act_signin_btn_check.isSelected
        }

        act_signin_btn.setOnClickListener {
            if (act_signin_edit_pwd.length() < 5) {
                act_signin_edit_id.setBackgroundResource(R.drawable.raspberry_opacity50_stroke_3)
                act_signin_edit_pwd.setBackgroundResource(R.drawable.raspberry_opacity50_stroke_3)
                act_signin_text_fail.visibility = View.VISIBLE
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}