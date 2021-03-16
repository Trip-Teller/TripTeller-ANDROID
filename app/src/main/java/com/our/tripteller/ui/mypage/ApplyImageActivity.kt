package com.our.tripteller.ui.mypage

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.bumptech.glide.Glide
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_apply_image.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class ApplyImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_image)

        btn_fin.setOnClickListener {
            val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val layout = inflater.inflate(R.layout.custom_toast, null)

            with(Toast(this)) {
                setGravity(Gravity.CENTER, 0, 0)
                duration = Toast.LENGTH_SHORT
                view = layout
                view.toast_message.text = "컨설턴트 등록을 완료했어요\uD83D\uDC4F\uD83C\uDFFB"
                show()
            }
            finish()
        }

        iv_img.setOnClickListener {
            val intent = Intent(this, SelectImageActivity::class.java)
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                123 -> {
                    val url = data!!.getStringExtra("url")
                    Glide.with(this).load(url).into(iv_img)
                    btn_fin.isEnabled = true
                }
            }
        }
    }
}