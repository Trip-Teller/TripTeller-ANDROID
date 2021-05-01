package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.RequestSignUpData
import com.our.tripteller.data.ResponseSignUpData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_profile_image.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody.*
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.InputStream

class ProfileImageActivity : AppCompatActivity() {

    val REQUEST_CODE = 0
    var profile_img = false
    lateinit var photo: Part

    val signupData = JSONObject()
    //val profileImage = RequestBody
    val requestToServer = RequestToServer.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_image)

        //이미지 설정
        act_profile_image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
        }

        act_profile_image_btn.setOnClickListener {
            startActivity(Intent(this@ProfileImageActivity, MainActivity::class.java))
            finish()
        }
    }


    //갤러리에서 이미지 가져오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this@ProfileImageActivity, data!!.dataString, Toast.LENGTH_SHORT).show()
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
            val selectedImg = data!!.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImg)
            val c = this!!.contentResolver.query(Uri.parse(selectedImg.toString()), null, null, null, null)
            c!!.moveToNext()
//            val absolutePath = c!!.getString(c!!.getColumnIndex(MediaStore.MediaColumns.DATA))
//            val file = File(absolutePath)
//            val rqFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//            photo = Part.createFormData("profileImage", file.name, rqFile)
            act_profile_image.setImageBitmap(bitmap)
            act_profile_image_raspberry.visibility = View.INVISIBLE
            profile_img = true
            act_profile_image_btn.isEnabled = true
        } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
                profile_img = false
        }
    }
}