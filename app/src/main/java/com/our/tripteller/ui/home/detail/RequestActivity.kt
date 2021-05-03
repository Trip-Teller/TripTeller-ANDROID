package com.our.tripteller.ui.home.detail

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_request.*
import java.util.*

class RequestActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        tv_start.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, year, month, day ->
                tv_start.text = (month+1).toString() + "월 " + day.toString() + "일"
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }

        tv_end.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, year, month, day ->
                tv_end.text = (month+1).toString() + "월" + day.toString() + "일"
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }

        btn_next.setOnClickListener {
            startActivity(Intent(this, RequestLastActivity::class.java))
        }
    }
}