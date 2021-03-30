package com.our.tripteller.ui.home.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_request_filter.*

class RequestFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_filter)

        btn_activity.setOnClickListener {
            btn_activity.isSelected = !btn_activity.isSelected
        }

        btn_healing.setOnClickListener {
            btn_healing.isSelected = !btn_healing.isSelected
        }

        btn_shopping.setOnClickListener {
            btn_shopping.isSelected = !btn_shopping.isSelected
        }

        btn_dish.setOnClickListener {
            btn_dish.isSelected = !btn_dish.isSelected
        }

        btn_picture.setOnClickListener {
            btn_picture.isSelected = !btn_picture.isSelected
        }

        btn_nature.setOnClickListener {
            btn_nature.isSelected = !btn_nature.isSelected
        }

        btn_pleasure.setOnClickListener {
            btn_pleasure.isSelected = !btn_pleasure.isSelected
        }

        btn_art.setOnClickListener {
            btn_art.isSelected = !btn_art.isSelected
        }

        btn_alone.setOnClickListener {
            btn_alone.isSelected = !btn_alone.isSelected
        }

        btn_couple.setOnClickListener {
            btn_couple.isSelected = !btn_couple.isSelected
        }

        btn_parents.setOnClickListener {
            btn_parents.isSelected = !btn_parents.isSelected
        }

        btn_friends.setOnClickListener {
            btn_friends.isSelected = !btn_friends.isSelected
        }

        btn_family.setOnClickListener {
            btn_family.isSelected = !btn_family.isSelected
        }

        btn_iconomy.setOnClickListener {
            btn_iconomy.isSelected = !btn_iconomy.isSelected
        }

        btn_walker.setOnClickListener {
            btn_walker.isSelected = !btn_walker.isSelected
        }

        btn_luxury.setOnClickListener {
            btn_luxury.isSelected = !btn_luxury.isSelected
        }

        btn_drive.setOnClickListener {
            btn_drive.isSelected = !btn_drive.isSelected
        }
    }
}