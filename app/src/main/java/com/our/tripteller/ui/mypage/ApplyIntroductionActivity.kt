package com.our.tripteller.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_apply_introduction.*
import kotlinx.android.synthetic.main.activity_signup.*

class ApplyIntroductionActivity : AppCompatActivity() {

    var title = false
    var introduction = false
    var dish = false
    var cafe = false
    var place = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_introduction)

        btn_next.setOnClickListener {
            val intent = Intent(this, ApplyImageActivity::class.java)
            startActivity(intent)
            finish()
        }
        et_title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    et_title.setBackgroundResource(R.drawable.raspberry_stroke_3)
                    title = false
                    tv_title_cnt.text = "(0/20)"
                    btn_next.isEnabled = false
                } else {
                    et_title.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                    title = true
                    tv_title_cnt.text = "(" + et_title.text.length + "/20)"
                    if(title && introduction && dish && cafe && place){
                        btn_next.isEnabled = true
                    }
                }
            }
        })
        et_introduction.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! < 100) {
                    et_introduction.setBackgroundResource(R.drawable.raspberry_stroke_3)
                    tv_error1.visibility = View.VISIBLE
                    introduction = false
                    tv_introduction_cnt.text = "(" + et_introduction.text.length + "/500)"
                    btn_next.isEnabled = false
                } else {
                    et_introduction.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                    tv_error1.visibility = View.GONE
                    introduction = true
                    tv_introduction_cnt.text = "(" + et_introduction.text.length + "/500)"
                    if(title && introduction && dish && cafe && place){
                        btn_next.isEnabled = true
                    }
                }
            }
        })
        et_restaurant.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! < 100) {
                    et_restaurant.setBackgroundResource(R.drawable.raspberry_stroke_3)
                    tv_error2.visibility = View.VISIBLE
                    dish = false
                    tv_restaurant_cnt.text = "(" + et_restaurant.text.length + "/500)"
                    btn_next.isEnabled = false
                } else {
                    et_restaurant.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                    tv_error2.visibility = View.GONE
                    dish = true
                    tv_restaurant_cnt.text = "(" + et_restaurant.text.length + "/500)"
                    if(title && introduction && dish && cafe && place){
                        btn_next.isEnabled = true
                    }
                }
            }
        })
        et_cafe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! < 100) {
                    et_cafe.setBackgroundResource(R.drawable.raspberry_stroke_3)
                    tv_error3.visibility = View.VISIBLE
                    cafe = false
                    tv_cafe_cnt.text = "(" + et_cafe.text.length + "/500)"
                    btn_next.isEnabled = false
                } else {
                    et_cafe.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                    tv_error3.visibility = View.GONE
                    cafe = true
                    tv_cafe_cnt.text = "(" + et_cafe.text.length + "/500)"
                    if(title && introduction && dish && cafe && place){
                        btn_next.isEnabled = true
                    }
                }
            }
        })
        et_place.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length!! < 100) {
                    et_place.setBackgroundResource(R.drawable.raspberry_stroke_3)
                    tv_error4.visibility = View.VISIBLE
                    place = false
                    tv_place_cnt.text = "(" + et_place.text.length + "/500)"
                    btn_next.isEnabled = false
                } else {
                    et_place.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                    tv_error4.visibility = View.GONE
                    place = true
                    tv_place_cnt.text = "(" + et_place.text.length + "/500)"
                    if(title && introduction && dish && cafe && place){
                        btn_next.isEnabled = true
                    }
                }
            }
        })
    }
}