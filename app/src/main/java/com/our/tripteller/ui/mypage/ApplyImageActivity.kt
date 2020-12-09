package com.our.tripteller.ui.mypage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
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
                view.toast_message.text = "\uD83D\uDC4F\uD83C\uDFFB\n컨설턴트 등록이 완료되었습니다"
                show()
            }
            finish()
        }
    }
}