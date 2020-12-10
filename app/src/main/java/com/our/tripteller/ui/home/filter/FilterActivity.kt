package com.our.tripteller.ui.home.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.our.tripteller.R
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        act_filter_btn_activity.isSelected = false
        act_filter_btn_healing.isSelected = false
        act_filter_btn_shopping.isSelected = false
        act_filter_btn_dish.isSelected = false
        act_filter_btn_picture.isSelected = false
        act_filter_btn_nature.isSelected = false
        act_filter_btn_pleasure.isSelected = false
        act_filter_btn_art.isSelected = false
        act_filter_btn_alone.isSelected = false
        act_filter_btn_couple.isSelected = false
        act_filter_btn_parents.isSelected = false
        act_filter_btn_family.isSelected = false
        act_filter_btn_friends.isSelected = false
        act_filter_btn_iconomy.isSelected = false
        act_filter_btn_walker.isSelected = false
        act_filter_btn_drive.isSelected = false
        act_filter_btn_luxury.isSelected = false

        act_filter_btn_activity.setOnClickListener {
            act_filter_btn_activity.isSelected = !act_filter_btn_activity.isSelected
        }
        act_filter_btn_healing.setOnClickListener {
            act_filter_btn_healing.isSelected = !act_filter_btn_healing.isSelected
        }
        act_filter_btn_shopping.setOnClickListener {
            act_filter_btn_shopping.isSelected = !act_filter_btn_shopping.isSelected
        }
        act_filter_btn_dish.setOnClickListener {
            act_filter_btn_dish.isSelected = !act_filter_btn_dish.isSelected
        }
        act_filter_btn_picture.setOnClickListener {
            act_filter_btn_picture.isSelected = !act_filter_btn_picture.isSelected
        }
        act_filter_btn_nature.setOnClickListener {
            act_filter_btn_nature.isSelected = !act_filter_btn_nature.isSelected
        }
        act_filter_btn_pleasure.setOnClickListener {
            act_filter_btn_pleasure.isSelected = !act_filter_btn_pleasure.isSelected
        }
        act_filter_btn_art.setOnClickListener {
            act_filter_btn_art.isSelected = !act_filter_btn_art.isSelected
        }
        act_filter_btn_alone.setOnClickListener {
            act_filter_btn_alone.isSelected = !act_filter_btn_alone.isSelected
        }
        act_filter_btn_couple.setOnClickListener {
            act_filter_btn_couple.isSelected = !act_filter_btn_couple.isSelected
        }
        act_filter_btn_parents.setOnClickListener {
            act_filter_btn_parents.isSelected = !act_filter_btn_parents.isSelected
        }
        act_filter_btn_family.setOnClickListener {
            act_filter_btn_family.isSelected = !act_filter_btn_family.isSelected
        }
        act_filter_btn_friends.setOnClickListener {
            act_filter_btn_friends.isSelected = !act_filter_btn_friends.isSelected
        }
        act_filter_btn_iconomy.setOnClickListener {
            act_filter_btn_iconomy.isSelected = !act_filter_btn_iconomy.isSelected
        }
        act_filter_btn_walker.setOnClickListener {
            act_filter_btn_walker.isSelected = !act_filter_btn_walker.isSelected
        }
        act_filter_btn_drive.setOnClickListener {
            act_filter_btn_drive.isSelected = !act_filter_btn_drive.isSelected
        }
        act_filter_btn_luxury.setOnClickListener {
            act_filter_btn_luxury.isSelected = !act_filter_btn_luxury.isSelected
        }
    }
}