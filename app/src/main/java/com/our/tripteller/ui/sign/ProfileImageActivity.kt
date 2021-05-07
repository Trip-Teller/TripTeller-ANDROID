package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.ResponseSignUpData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_profile_image.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ProfileImageActivity : AppCompatActivity() {

    val REQUEST_CODE = 0
    var profile_img = false

//    val signinData = JSONObject()
    var path = ""
    lateinit var currentImageUri: Uri
    val requestToServer = RequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        //이미지 설정
        act_profile_noimage.setOnClickListener {
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(intent, REQUEST_CODE)
            if (Build.VERSION.SDK_INT < 19) {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(intent, REQUEST_CODE)
            } else {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                startActivityForResult(intent, REQUEST_CODE)
            }
        }

        act_profile_image_btn.setOnClickListener {

//            signinData.put("age", "${intent.getIntExtra("age", 111)}")
//            signinData.put("birthDate", intent.getStringExtra("birthDate"))
//            signinData.put("password", intent.getStringExtra("password"))
//            signinData.put("gender", intent.getStringExtra("gender"))
//            signinData.put("email", intent.getStringExtra("email"))
//            signinData.put("nickname", intent.getStringExtra("nickname"))
//
//            val body = JsonParser.parseString(signinData.toString()) as JsonObject

            val image = File(currentImageUri.path)	// 경로 예시 : /storage/emulated/0/Download/filename.pdf
            Log.d("여기는 밖에서의 image 경로1", currentImageUri.path)
            Log.d("여기는 밖에서의 image 경로2", image.absolutePath)
            val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), image)

            val file = MultipartBody.Part.createFormData("profileImage", image.name, requestFile)

            Log.d("통신", intent.getIntExtra("age", 111).toString())
            Log.d("통신", intent.getStringExtra("birthDate"))
            Log.d("통신", intent.getStringExtra("password"))
            Log.d("통신", intent.getStringExtra("gender"))
            Log.d("통신", intent.getStringExtra("email"))
            Log.d("통신", intent.getStringExtra("nickname"))
            Log.d("통신", file.toString())

            if (profile_img){
                requestToServer.service.requestSignup(
                    intent.getIntExtra("age", 111),
                    intent.getStringExtra("birthDate"),
                    intent.getStringExtra("password"),
                    intent.getStringExtra("gender"),
                    intent.getStringExtra("email"),
                    intent.getStringExtra("nickname"),
                    file
                ).enqueue(object: Callback<ResponseSignUpData> {
                    override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                    ) {
                        if (response.isSuccessful){
                            val intent = Intent(this@ProfileImageActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            Log.d("통신 성공", "${response.body()}")

                        }
                        Log.d("통신 성공인가 실패인가", "${response.body()}")
                        Log.d("통신 성공인가 실패인가", "${response.errorBody()}")

                    }
                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("통신 에러", "에러에러에러. ${call}, ${t}")
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

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                currentImageUri = data?.data!!
                try {

//                    val `in`: InputStream? =
//                        data?.data?.let { contentResolver.openInputStream(it) }
//                    val img = BitmapFactory.decodeStream(`in`)
//                    `in`?.close()

                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUri)

                    act_profile_image.setImageBitmap(bitmap)

//                    if (currentImageUri != null) {
//                        path = currentImageUri.path.toString()
//                        Log.d("Uri의 path", path)
//                    }

                    act_profile_noimage.visibility = View.INVISIBLE
                    act_profile_image_raspberry.visibility = View.INVISIBLE

                    profile_img = true
                } catch (e: Exception) {
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
                profile_img = false

            }
        }


    }

    // 절대경로 변환
    fun absolutelyPath(path: Uri): String {

        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = contentResolver.query(path, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        return index?.let { c.getString(it) }.toString()

//        val proj = arrayOf(MediaStore.Images.Media.DATA)
//        val c = managedQuery(path, proj, null, null, null)
//        val index = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//
//        c.moveToFirst()
    }
}