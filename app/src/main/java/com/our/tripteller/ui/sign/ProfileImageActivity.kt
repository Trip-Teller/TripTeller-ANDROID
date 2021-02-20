package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_profile_image.*
import java.io.InputStream

class ProfileImageActivity : AppCompatActivity() {

    val REQUEST_CODE = 0
    var profile_img = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        //이미지 설정
        act_profile_noimage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
        }

        act_profile_image_btn.setOnClickListener {
            if (profile_img){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "이미지 등록하셈", Toast.LENGTH_SHORT).show()
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


    }
}