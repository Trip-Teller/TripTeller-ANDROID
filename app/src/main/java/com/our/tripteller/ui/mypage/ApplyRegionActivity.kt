package com.our.tripteller.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_apply_region.*

class ApplyRegionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_region)

        btn_next.setOnClickListener {
            val intent = Intent(this, ApplyFilterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}