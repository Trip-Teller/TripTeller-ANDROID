package com.our.tripteller.ui.mypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_apply_region.*

class ApplyRegionActivity : AppCompatActivity() {

    lateinit var region : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_region)

        btn_next.setOnClickListener {
            val intent = Intent(this, ApplyFilterActivity::class.java)
            startActivity(intent)
            finish()
        }

        rg_region.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.btn_busan -> region = "부산"
                R.id.btn_seoul -> region = "서울"
                R.id.btn_jeju -> region = "제주도"
                R.id.btn_gangwon -> region = "강원도"
                R.id.btn_jeolla -> region = "전라도"
                R.id.btn_gyeongsang -> region = "경상도"
                R.id.btn_chungcheong -> region = "충청도"
                R.id.btn_gyounggi -> region = "경기도"
            }
            btn_next.isEnabled = true
        }
    }
}