package com.our.tripteller.ui.sign

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.InputStream


class ProfileActivity : AppCompatActivity() {

    val REQUEST_CODE = 0

    var profile_img = false
    var nick = false
    var age = false
    var sex = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        //이미지 설정
        act_profile_image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
        }

        //닉네임 설정
        act_profile_edit_nickname.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                // 입력난에 변화가 있을 시 조치
                act_profile_edit_nickname.setBackgroundResource(R.drawable.iceblue_roundbox_24)
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
        act_profile_const.setOnClickListener {
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
        act_profile_btn_done.setOnClickListener {

            if (!profile_img) {
                act_profile_image_raspberry.visibility = View.VISIBLE
            }

            nick = if (act_profile_edit_nickname.text.isNullOrBlank()) {
                act_profile_edit_nickname.setBackgroundResource(R.drawable.raspberry_stroke_24)
                false
            } else {
                act_profile_edit_nickname.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                true
            }

            if (act_profile_text_year.text == "") {
                act_profile_text_year.setBackgroundResource(R.drawable.raspberry_stroke_24)
                act_profile_text_month.setBackgroundResource(R.drawable.raspberry_stroke_24)
                act_profile_text_day.setBackgroundResource(R.drawable.raspberry_stroke_24)
                age = false
            }

            if (!act_profile_btn_women.isSelected && !act_profile_btn_man.isSelected) {
                act_profile_btn_women.setBackgroundResource(R.drawable.raspberry_stroke_24)
                act_profile_btn_man.setBackgroundResource(R.drawable.raspberry_stroke_24)
            }
            sex = act_profile_btn_women.isSelected || act_profile_btn_man.isSelected

            if (profile_img && nick && age && sex) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }


    //갤러리에서 이미지 가져오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === REQUEST_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                try {
                    val `in`: InputStream? =
                        data?.data?.let { contentResolver.openInputStream(it) }
                    val img = BitmapFactory.decodeStream(`in`)
                    `in`?.close()

                    act_profile_image.setImageBitmap(img)

                    act_profile_noimage.visibility = View.INVISIBLE
                    act_profile_image_raspberry.visibility = View.INVISIBLE

                    profile_img = true
                } catch (e: Exception) {
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
                profile_img = false

            }
        }

        if (requestCode == 200 && resultCode == 777){
            act_profile_text_year.text = data?.getIntExtra("year", 0).toString()
            act_profile_text_month.text = data?.getIntExtra("month", 0).toString()
            act_profile_text_day.text = data?.getIntExtra("day", 0).toString()

            act_profile_text_year.setBackgroundResource(R.drawable.iceblue_roundbox_24)
            act_profile_text_month.setBackgroundResource(R.drawable.iceblue_roundbox_24)
            act_profile_text_day.setBackgroundResource(R.drawable.iceblue_roundbox_24)

            age = true
        }
    }

}