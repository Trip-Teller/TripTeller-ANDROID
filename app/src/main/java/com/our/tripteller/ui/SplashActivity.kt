package com.our.tripteller.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.IMAGES_RESOURCE
import com.our.tripteller.ui.sign.SigninActivity
import com.our.tripteller.ui.sign.SignupActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val datas = IMAGES_RESOURCE

        val rnds = (datas.indices).random()
        act_splash_kenBurnsView.setImageResource(datas[rnds])

        act_splash_btn_signin.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            intent.putExtra("img", rnds)
            startActivity(intent)
            finish()
        }

        act_splash_btn_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        act_splash_btn_justlook.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}