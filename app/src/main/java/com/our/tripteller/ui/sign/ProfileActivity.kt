package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.InputStream


class ProfileActivity : AppCompatActivity() {

    val REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var nick = false
        var age = false
        var sex = false

        //이미지 설정
        act_profile_image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
        }

        //나이 설정
        val spinner: Spinner = findViewById(R.id.act_profile_spinner_age)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.age,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinner.onItemSelectedListener = this

                if (spinner.selectedItem.toString()!="선택") {
                    spinner.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                    age = true
                }
            }

        }



        //성별 설정 - 여자
        act_profile_btn_women.setOnClickListener {
            if (!act_profile_btn_man.isSelected) {
                act_profile_btn_women.isSelected = !act_profile_btn_women.isSelected
            } else {
                act_profile_btn_women.isSelected = !act_profile_btn_women.isSelected
                act_profile_btn_man.isSelected = false
            }
        }
        //성별 설정 - 남자
        act_profile_btn_man.setOnClickListener {
            if (!act_profile_btn_women.isSelected) {
                act_profile_btn_man.isSelected = !act_profile_btn_man.isSelected
            } else {
                act_profile_btn_man.isSelected = !act_profile_btn_man.isSelected
                act_profile_btn_women.isSelected = false
            }
        }

        //완료 버튼
        act_profile_btn_done.setOnClickListener {

            nick = if (act_profile_edit_nickname.text.isNullOrBlank()){
                act_profile_edit_nickname.setBackgroundResource(R.drawable.raspberry_stroke_24)
                false
            }else{
                act_profile_edit_nickname.setBackgroundResource(R.drawable.iceblue_roundbox_24)
                true
            }

            if (act_profile_spinner_age.selectedItem.toString() == "선택"){
                act_profile_spinner_age.setBackgroundResource(R.drawable.raspberry_stroke_24)
                age = false
            }

            sex = act_profile_btn_women.isSelected || act_profile_btn_man.isSelected

            if( nick && age && sex){
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

                } catch (e: Exception) {
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
            }
        }
    }
}