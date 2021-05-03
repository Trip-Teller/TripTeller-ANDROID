package com.our.tripteller.ui.home.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_request_filter.*

class RequestFilterActivity : AppCompatActivity() {
    private var filterList = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_filter)

        btn_activity.setOnClickListener {
            check(btn_activity)
            nextEnable(btn_next)
        }

        btn_healing.setOnClickListener {
            check(btn_healing)
            nextEnable(btn_next)
        }

        btn_shopping.setOnClickListener {
            check(btn_shopping)
            nextEnable(btn_next)
        }

        btn_dish.setOnClickListener {
            check(btn_dish)
            nextEnable(btn_next)
        }

        btn_picture.setOnClickListener {
            check(btn_picture)
            nextEnable(btn_next)
        }

        btn_nature.setOnClickListener {
            check(btn_nature)
            nextEnable(btn_next)
        }

        btn_pleasure.setOnClickListener {
            check(btn_pleasure)
            nextEnable(btn_next)
        }

        btn_art.setOnClickListener {
            check(btn_art)
            nextEnable(btn_next)
        }

        btn_alone.setOnClickListener {
            check(btn_alone)
            nextEnable(btn_next)
        }

        btn_couple.setOnClickListener {
            check(btn_couple)
            nextEnable(btn_next)
        }

        btn_parents.setOnClickListener {
            check(btn_parents)
            nextEnable(btn_next)
        }

        btn_friends.setOnClickListener {
            check(btn_friends)
            nextEnable(btn_next)
        }

        btn_family.setOnClickListener {
            check(btn_family)
            nextEnable(btn_next)
        }

        btn_iconomy.setOnClickListener {
            check(btn_iconomy)
            nextEnable(btn_next)
        }

        btn_walker.setOnClickListener {
            check(btn_walker)
            nextEnable(btn_next)
        }

        btn_luxury.setOnClickListener {
            check(btn_luxury)
            nextEnable(btn_next)
        }

        btn_drive.setOnClickListener {
            check(btn_drive)
            nextEnable(btn_next)
        }

        btn_next.setOnClickListener {
            startActivity(Intent(this, RequestActivity::class.java))
        }
    }
    private fun check(v: TextView){
        if(v.isSelected){
            filterList.remove(v.text.toString())
            v.isSelected = !v.isSelected
            show()
        }
        else{
            addFilter(v)
        }
    }
    private fun addFilter(v: TextView){
        if(filterList.isNotEmpty() && filterList.size == 3)
            Toast.makeText(this, "최대 3개까지 선택 가능합니다.",Toast.LENGTH_SHORT).show()
        else{
            filterList.add(v.text.toString())
            v.isSelected = !v.isSelected
            show()
        }
    }

    private fun show(){
        var str = ""
        for(s in filterList)
            str += "$s  "
        tv_filter_list.text = str
    }

    private fun nextEnable(v: Button){
        v.isEnabled = filterList.isNotEmpty()
    }
}