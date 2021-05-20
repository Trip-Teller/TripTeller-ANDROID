package com.our.tripteller.ui.sign

import android.annotation.SuppressLint
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

            val image = File(getPathFromUri(currentImageUri).toString())	// 경로 예시 : /storage/emulated/0/Download/filename.pdf
            Log.d("여기는 밖에서의 image 경로1",getPathFromUri(currentImageUri).toString())
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), image)

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

                        } else {
                            Log.d("통신 성공인가 실패인가", "${response.body()}")
                        }
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

    @SuppressLint("Recycle")
    private fun getPathFromUri(uri: Uri?): String? {
        val cursor: Cursor = contentResolver.query(uri!!, null, null, null, null)!!
        val path = cursor.getString(cursor.getColumnIndex("_data"))
        Log.d("cursor name1", cursor.getColumnName(1))
        Log.d("cursor name2", cursor.getColumnName(2))
        Log.d("cursor name3", cursor.getColumnName(3))
        Log.d("cursor name4", cursor.getColumnName(4))
        Log.d("cursor name5", cursor.getColumnName(5))
        Log.d("cursor name6", cursor.getColumnName(6))
        cursor.close()
        return path
    }

//    fun getRealPathFromURI(context: Context, uri: Uri): String? {
//
//        // DocumentProvider
//        if (DocumentsContract.isDocumentUri(context, uri)) {
//
//            // ExternalStorageProvider
//            if (isExternalStorageDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                return if ("primary".equals(type, ignoreCase = true)) {
//                    (Environment.getExternalStorageDirectory().toString() + "/"
//                            + split[1])
//                } else {
//                    val SDcardpath = getRemovableSDCardPath(context)?.split("/Android".toRegex())!!.toTypedArray()[0]
//                    SDcardpath + "/" + split[1]
//                }
//            } else if (isDownloadsDocument(uri)) {
//                val id = DocumentsContract.getDocumentId(uri)
//                val contentUri = ContentUris.withAppendedId(
//                    Uri.parse("content://downloads/public_downloads"),
//                    java.lang.Long.valueOf(id))
//                return getDataColumn(context, contentUri, null, null)
//            } else if (isMediaDocument(uri)) {
//                val docId = DocumentsContract.getDocumentId(uri)
//                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
//                val type = split[0]
//                var contentUri: Uri? = null
//                if ("image" == type) {
//                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                } else if ("video" == type) {
//                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
//                } else if ("audio" == type) {
//                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
//                }
//                val selection = "_id=?"
//                val selectionArgs = arrayOf(split[1])
//                return getDataColumn(context, contentUri, selection,
//                    selectionArgs)
//            }
//        } else {
//            if ("content".equals(uri.getScheme(), ignoreCase = true)) {
//                // Return the remote address
//                return if (isGooglePhotosUri(uri)) uri.getLastPathSegment() else getDataColumn(context, uri, null, null)
//            } else if ("file".equals(uri.getScheme(), ignoreCase = true)) {
//                return uri.path
//            }
//        }
//        return null
//    }
//
//    fun getRemovableSDCardPath(context: Context?): String? {
//        val storages =
//            ContextCompat.getExternalFilesDirs(context!!, null)
//        return if (storages.size > 1 && storages[0] != null && storages[1] != null) storages[1].toString() else ""
//    }
//
//    fun getDataColumn(
//        context: Context, uri: Uri?,
//        selection: String?, selectionArgs: Array<String?>?
//    ): String? {
//        var cursor: Cursor? = null
//        val column = "_data"
//        val projection = arrayOf(column)
//        try {
//            cursor = context.contentResolver.query(
//                uri!!, projection,
//                selection, selectionArgs, null
//            )
//            if (cursor != null && cursor.moveToFirst()) {
//                val index = cursor.getColumnIndexOrThrow(column)
//                return cursor.getString(index)
//            }
//        } finally {
//            cursor?.close()
//        }
//        return null
//    }
//
//
//    fun isExternalStorageDocument(uri: Uri): Boolean {
//        return "com.android.externalstorage.documents" == uri
//            .authority
//    }
//
//
//    fun isDownloadsDocument(uri: Uri): Boolean {
//        return "com.android.providers.downloads.documents" == uri
//            .authority
//    }
//
//
//    fun isMediaDocument(uri: Uri): Boolean {
//        return "com.android.providers.media.documents" == uri
//            .authority
//    }
//
//
//    fun isGooglePhotosUri(uri: Uri): Boolean {
//        return "com.google.android.apps.photos.content" == uri
//            .authority
//    }

}