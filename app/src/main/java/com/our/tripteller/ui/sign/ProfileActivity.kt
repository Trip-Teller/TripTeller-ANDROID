package com.our.tripteller.ui.sign

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    var nick = false
    var age = false
    var sex = false

    var nickname = ""
    var gender = ""
    var birthDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //이전 회원가입에서 id, password 받아온다.
        val id = intent.getStringExtra("id")
        val password = intent.getStringExtra("password")

        //닉네임 설정
        act_profile_edit_nickname.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                // 입력난에 변화가 있을 시 조치
                act_profile_edit_nickname.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
            }

            override fun afterTextChanged(arg0: Editable) {
                // 입력이 끝났을 때 조치
                //act_profile_edit_nickname.setBackgroundResource(R.drawable.iceblue_roundbox_24)
            }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // 입력하기 전에 조치
            }
        })


        //나이 설정
        act_profile_const1.setOnClickListener {
            val intent = Intent(this, DatePickerActivity::class.java)
            startActivityForResult(intent, 200)
        }

        //성별 설정 - 여자
        act_profile_btn_women.setOnClickListener {
            act_profile_btn_man.setBackgroundResource(R.drawable.selector_profile_sex)
            act_profile_btn_women.setBackgroundResource(R.drawable.selector_profile_sex)

            if (!act_profile_btn_man.isSelected) {
                act_profile_btn_women.isSelected = !act_profile_btn_women.isSelected
            } else {
                act_profile_btn_women.isSelected = !act_profile_btn_women.isSelected
                act_profile_btn_man.isSelected = false
            }
        }
        //성별 설정 - 남자
        act_profile_btn_man.setOnClickListener {
            act_profile_btn_man.setBackgroundResource(R.drawable.selector_profile_sex)
            act_profile_btn_women.setBackgroundResource(R.drawable.selector_profile_sex)

            if (!act_profile_btn_women.isSelected) {
                act_profile_btn_man.isSelected = !act_profile_btn_man.isSelected
            } else {
                act_profile_btn_man.isSelected = !act_profile_btn_man.isSelected
                act_profile_btn_women.isSelected = false
            }
        }

        //완료 버튼
        act_profile_btn_next.setOnClickListener {

            nick = if (act_profile_edit_nickname.text.isNullOrBlank()) {
                act_profile_edit_nickname.setBackgroundResource(R.drawable.raspberry_stroke_3)
                false
            } else {
                act_profile_edit_nickname.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
                true
            }

            if (act_profile_text_year.text == "") {
                act_profile_text_year.setBackgroundResource(R.drawable.raspberry_stroke_3)
                act_profile_text_month.setBackgroundResource(R.drawable.raspberry_stroke_3)
                act_profile_text_day.setBackgroundResource(R.drawable.raspberry_stroke_3)
                age = false
            }

            if (!act_profile_btn_women.isSelected && !act_profile_btn_man.isSelected) {
                act_profile_btn_women.setBackgroundResource(R.drawable.raspberry_stroke_3)
                act_profile_btn_man.setBackgroundResource(R.drawable.raspberry_stroke_3)
            }
            sex = act_profile_btn_women.isSelected || act_profile_btn_man.isSelected

            if (nick && age && sex) {
                nickname = act_profile_edit_nickname.text.toString()

                gender = if (act_profile_btn_women.isSelected) {
                    "f"
                } else if (act_profile_btn_man.isSelected) {
                    "m"
                } else {
                    "o"
                }

                birthDate = "${act_profile_text_year.text}-${act_profile_text_month.text}-${act_profile_text_day.text}"

                Log.d("SignUp 2 ", "$id $password $nickname $gender $birthDate ${2021-act_profile_text_year.text.toString().toInt()+1}")
                val intent = Intent(this, ProfileImageActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("password", password)
                intent.putExtra("nickname", nickname)
                intent.putExtra("gender", gender)
                intent.putExtra("birthDate", birthDate)
                intent.putExtra("age", 2021-act_profile_text_year.text.toString().toInt()+1)

                startActivity(intent)
//                finish()
            }

        }

    }

    //set date picker
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200){
            act_profile_text_year.text = data?.getIntExtra("year", 0).toString()
            act_profile_text_month.text = data?.getIntExtra("month", 0).toString()
            act_profile_text_day.text = data?.getIntExtra("day", 0).toString()

            act_profile_text_year.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
            act_profile_text_month.setBackgroundResource(R.drawable.skyblue_roundstroke_3)
            act_profile_text_day.setBackgroundResource(R.drawable.skyblue_roundstroke_3)

            age = true
        }
    }

}