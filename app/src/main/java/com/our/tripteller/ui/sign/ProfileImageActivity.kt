package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.ResponseSignUpData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_profile_image.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.MultipartBody.*
import okhttp3.RequestBody
import okhttp3.RequestBody.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.InputStream

class ProfileImageActivity : AppCompatActivity() {

    val REQUEST_CODE = 0
    var profile_img = false

    val signupData = JSONObject()
    val profileImage = RequestBody
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

//            val data: HashMap<String?, Any?> = HashMap()
//            data["age"] = (intent.getIntExtra("age", 111))
//            data["birthDate"] = (intent.getStringExtra("birthDate")).toRequestBody("text/plain".toMediaTypeOrNull())
//            data["password"] = (intent.getStringExtra("password")).toRequestBody("text/plain".toMediaTypeOrNull())
//            data["gender"] = (intent.getStringExtra("gender")).toRequestBody("text/plain".toMediaTypeOrNull())
//            data["email"] = (intent.getStringExtra("email")).toRequestBody("text/plain".toMediaTypeOrNull())
//            data["nickname"] = (intent.getStringExtra("nickname")).toRequestBody("text/plain".toMediaTypeOrNull())
//
//            signupData.put("age", intent.getIntExtra("age", 111))
//            signupData.put("birthDate", intent.getStringExtra("birthDate"))
//            signupData.put("password", intent.getStringExtra("password"))
//            signupData.put("gender", intent.getStringExtra("gender"))
//            signupData.put("email", intent.getStringExtra("email"))
//            signupData.put("nickname", intent.getStringExtra("nickname"))
//            signupData.put("profileImage", " ")
//
//            val body = JsonParser.parseString(signupData.toString()) as JsonObject

//            val a = Builder().addFormDataPart("body", signupData.toString())

//                val requestBody: RequestBody = MultipartBody.Part
//                    .createFormData("age", intent.getIntExtra("age", 111).toString())
//                    .createFormData("birthDate", intent.getStringExtra("birthDate") as String)
//                    .createFormData("password", intent.getStringExtra("password") as String)
//                    .createFormData("gender", intent.getStringExtra("gender") as String)
//                    .createFormData("email", if (intent.getStringExtra("email")==null) "null" else intent.getStringExtra("email") )
//                    .createFormData("nickname", intent.getStringExtra("nickname") as String)
//                    .createFormData("profileImage", "")
//                    .build()

            val file = File("image/download/*")
            val age = intent.getIntExtra("age", 111).toString()
                .toRequestBody("text/plain".toMediaTypeOrNull())
            val birthDate = intent.getStringExtra("birthDate")!!
                .toRequestBody("text/plain".toMediaTypeOrNull())
            val password = intent.getStringExtra("password")!!
                .toRequestBody("text/plain".toMediaTypeOrNull())
            val gender = intent.getStringExtra("gender")!!
                .toRequestBody("text/plain".toMediaTypeOrNull())
            val email = (if (intent.getStringExtra("email")==null) "no email" else intent.getStringExtra("email"))
                .toRequestBody("text/plain".toMediaTypeOrNull())
            val nickname = intent.getStringExtra("nickname")!!
                .toRequestBody("text/plain".toMediaTypeOrNull())

            val profile: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
            val profileImage = Part.createFormData("profileImage", file.name, profile)

            requestToServer.service.requestSignup(
                age, birthDate, password, gender,
                email, nickname, profileImage
            ).enqueue(object : Callback<ResponseSignUpData> {
                override fun onResponse(
                    call: Call<ResponseSignUpData>,
                    response: Response<ResponseSignUpData>
                ) {
                    if (response.isSuccessful) {
                        Log.d("통신 성공", "$response")
                        val intent =
                            Intent(this@ProfileImageActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d("통신 실패 ", "$response")
                    }
                }
                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    Log.d("통신 에러", "에러에러에러")
                }
            })

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