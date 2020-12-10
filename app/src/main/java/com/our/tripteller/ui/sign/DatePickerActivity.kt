package com.our.tripteller.ui.sign

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_date_picker.*
import kotlinx.android.synthetic.main.activity_profile.*

class DatePickerActivity : Activity() {

    var year = 0
    var month = 0
    var day = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Popup의 Title을 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_date_picker)

        act_datepicker_btn_ok.setOnClickListener {
            year = act_datepicker.year
            month = act_datepicker.month + 1
            day = act_datepicker.dayOfMonth

            val intent = Intent()
            intent.putExtra("year", year)
            intent.putExtra("month", month)
            intent.putExtra("day", day)
            setResult(777, intent)

            finish()
        }
    }
}