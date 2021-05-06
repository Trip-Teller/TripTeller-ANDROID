package com.our.tripteller.ui.home.detail

import android.app.Activity
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
                val m: String = if(month + 1 < 10) {
                    "0" + (month + 1).toString()
                } else{
                    (month + 1).toString()
                }
                val d: String = if(day < 10){
                    "0$day"
                } else {
                    day.toString()
                }
                tv_start.text = year.toString() + "." + m + "." + d
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }

        tv_end.setOnClickListener {
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{datePicker, year, month, day ->
                val m: String = if(month + 1 < 10) {
                    "0" + (month + 1).toString()
                } else{
                    (month + 1).toString()
                }
                val d: String = if(day < 10){
                    "0$day"
                } else {
                    day.toString()
                }
                tv_end.text = year.toString() + "." + m + "." + d
            }, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();

        }

        btn_next.setOnClickListener {
            startActivity(Intent(this, RequestLastActivity::class.java))
            finish()
        }
    }
}