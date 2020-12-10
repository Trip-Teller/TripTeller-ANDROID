package com.our.tripteller.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.IMAGES_RESOURCE
import com.our.tripteller.ui.sign.SignupActivity
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.layout_signin.*
import kotlinx.android.synthetic.main.layout_signin.act_signin_btn
import kotlinx.android.synthetic.main.layout_signin.act_signin_btn_check
import kotlinx.android.synthetic.main.layout_signin.act_signin_edit_id
import kotlinx.android.synthetic.main.layout_signin.act_signin_edit_pwd
import kotlinx.android.synthetic.main.layout_signin.act_signin_text_fail

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val datas = IMAGES_RESOURCE

        layout_signin.visibility = View.INVISIBLE


        val rnds = (datas.indices).random()
        act_splash_kenBurnsView.setImageResource(datas[rnds])

        layout_signin_top.setOnClickListener {
            slideDown(layout_signin)
            act_splash_img.visibility = View.VISIBLE

            act_splash_btn_signin.isClickable = true
            act_splash_btn_justlook.isClickable = true
            act_splash_btn_signup.isClickable = true
        }


        act_splash_btn_signin.setOnClickListener {

            slideUp(layout_signin)
            act_splash_img.visibility = View.INVISIBLE

            act_splash_btn_signin.isClickable = false
            act_splash_btn_justlook.isClickable = false
            act_splash_btn_signup.isClickable = false

            act_signin_edit_id.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    act_signin_edit_id.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                    act_signin_text_fail.visibility = View.GONE
                }
            })

            act_signin_edit_pwd.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    act_signin_edit_pwd.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                    act_signin_text_fail.visibility = View.GONE
                }
            })


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

        act_splash_btn_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        act_splash_btn_justlook.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    // slide the view from below itself to the current position
    fun slideUp(view: View) {
        view.visibility = View.VISIBLE

        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = false
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = false
        view.startAnimation (animate)

        view.visibility = View.INVISIBLE
    }
}