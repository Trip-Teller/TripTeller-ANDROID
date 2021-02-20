package com.our.tripteller.ui.mypage

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.our.tripteller.R
import com.our.tripteller.data.ImageInfoData
import com.our.tripteller.data.ResponseUnsplashData
import com.our.tripteller.network.RequestToServer
import kotlinx.android.synthetic.main.activity_select_image.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class SelectImageActivity : AppCompatActivity() {

    lateinit var selectImageAdapter: SelectImageAdapter
    val selectImageData = mutableListOf<ImageInfoData>()
    val service = RequestToServer.serviceUnsplash
    var page: Int = 1
    var totalpage by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)

        selectImageAdapter = SelectImageAdapter(this) { ImageInfoData, view: View ->
            intent.putExtra("url", ImageInfoData.urls.regular)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        rv_unsplash.adapter = selectImageAdapter

        btn_search.setOnClickListener {
            showView()
        }

        btn_plus.setOnClickListener {
            plusView()
        }
    }

    private fun showView(){
        page = 1
        val query = mutableMapOf<String, String>()
        val query02 = mutableMapOf<String, Int>()
        query["query"] = et_search_bar.text.toString()
        query["client_id"] = "TIHjOft38wXlq6jJrwqfJWoBZ7DscJq-R7A9gQzCG3s"
        query02["page"] = page
        query02["per_page"] = 30
        service.requestBackgroundImg(query, query02).enqueue(object : Callback<ResponseUnsplashData> {//Callback등록. Retrofit의 Callback을 import 해줘야 함!
        override fun onFailure(call: Call<ResponseUnsplashData>, t: Throwable) { //통신 실패
        }
            override fun onResponse(
                call: Call<ResponseUnsplashData>,
                response: Response<ResponseUnsplashData>
            ) { //통신 성공
                if(response.isSuccessful) {//statusCode가 200~300 사이일때. 응답 body 이용 가능
                    totalpage = response.body()!!.total_pages
                    selectImageData.clear()
                    selectImageData.addAll(response.body()!!.results)
                    selectImageAdapter.datas = selectImageData
                    selectImageAdapter.notifyDataSetChanged()
                    if(page != totalpage && totalpage != 0)
                        btn_plus.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun plusView(){
        page += 1
        val query = mutableMapOf<String, String>()
        val query02 = mutableMapOf<String, Int>()
        query["query"] = et_search_bar.text.toString()
        query["client_id"] = "TIHjOft38wXlq6jJrwqfJWoBZ7DscJq-R7A9gQzCG3s"
        query02["page"] = page
        query02["per_page"] = 30
        service.requestBackgroundImg(query, query02).enqueue(object : Callback<ResponseUnsplashData> {//Callback등록. Retrofit의 Callback을 import 해줘야 함!
        override fun onFailure(call: Call<ResponseUnsplashData>, t: Throwable) { //통신 실패
        }
            override fun onResponse(
                call: Call<ResponseUnsplashData>,
                response: Response<ResponseUnsplashData>
            ) { //통신 성공
                if(response.isSuccessful) {//statusCode가 200~300 사이일때. 응답 body 이용 가능
                    selectImageData.addAll(response.body()!!.results)
                    selectImageAdapter.datas = selectImageData
                    selectImageAdapter.notifyDataSetChanged()
                }
            }
        })
        if(page == totalpage)
            btn_plus.visibility = View.INVISIBLE
    }

}