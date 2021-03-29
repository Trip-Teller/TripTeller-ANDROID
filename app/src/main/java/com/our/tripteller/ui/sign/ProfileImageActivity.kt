package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.ResponseSignUpData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_profile_image.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.*
import okhttp3.RequestBody
import okhttp3.RequestBody.*
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class ProfileImageActivity : AppCompatActivity() {

    val REQUEST_CODE = 0
    var profile_img = false

    var profileImage = MultipartBody.Part.createFormData("profileImage", "img")

    val requestToServer = RequestToServer

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

            val data: HashMap<String?, Any?> = HashMap()
            data["age"] = (intent.getIntExtra("age", 111))
            data["birthDate"] = (intent.getStringExtra("birthDate")).toRequestBody("text/plain".toMediaTypeOrNull())
            data["password"] = (intent.getStringExtra("password")).toRequestBody("text/plain".toMediaTypeOrNull())
            data["gender"] = (intent.getStringExtra("gender")).toRequestBody("text/plain".toMediaTypeOrNull())
            data["email"] = (intent.getStringExtra("email")).toRequestBody("text/plain".toMediaTypeOrNull())
            data["nickname"] = (intent.getStringExtra("nickname")).toRequestBody("text/plain".toMediaTypeOrNull())

            if (profile_img){
                requestToServer.service.requestSignup(
                    data
                ).enqueue(object: Callback<ResponseSignUpData> {
                    override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                    ) {
                        Log.d("통신 성공", "$response")
                        val intent = Intent(this@ProfileImageActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("통신 에러", "에러에러에러")
                    }

                })
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