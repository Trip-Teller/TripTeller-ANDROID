package com.our.tripteller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.our.tripteller.ui.chat.ChatFragment
import com.our.tripteller.ui.home.MainFragment
import com.our.tripteller.ui.mypage.MypageFragment

class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MainFragment()
            1 -> ChatFragment()
            else -> MypageFragment()
        }
    }

    override fun getCount(): Int = 3
}