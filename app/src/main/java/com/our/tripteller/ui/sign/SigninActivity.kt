package com.our.tripteller.ui.sign

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.IMAGES_RESOURCE
import com.our.tripteller.data.ResponseSignInData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_signin.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SigninActivity : AppCompatActivity() {

    val loginData = JSONObject()
    val requestToServer = RequestToServer

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
        act_signin_btn_check.isSelected = true


        act_signin_btn_check.setOnClickListener {
            act_signin_btn_check.isSelected = !act_signin_btn_check.isSelected
        }

        act_signin_btn.setOnClickListener {
            loginData.put("email", "${act_signin_edit_id.text}")
            loginData.put("password", "${act_signin_edit_pwd.text}")

            val body = JsonParser.parseString(loginData.toString()) as JsonObject

            if (act_signin_edit_pwd.length() < 3) {
                act_signin_edit_id.setBackgroundResource(R.drawable.raspberry_opacity50_stroke_3)
                act_signin_edit_pwd.setBackgroundResource(R.drawable.raspberry_opacity50_stroke_3)
                act_signin_text_fail.visibility = View.VISIBLE
            } else {
                val intent = Intent(this@SigninActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                requestToServer.service.requestSignin(
                    body
                ).enqueue(object : Callback<ResponseSignInData>{
                    override fun onResponse(
                        call: Call<ResponseSignInData>,
                        response: Response<ResponseSignInData>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("통신 성공", "${response.body()}")
                            val intent = Intent(this@SigninActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("통신 실패 ", "아디, 비번 확인해")
                            Toast.makeText(this@SigninActivity, "아이디, 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<ResponseSignInData>, t: Throwable) {
                        Log.d("통신 실패 ", "통신 그냥 실패")
                        Toast.makeText(this@SigninActivity, "통신 실패", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }
    }
}