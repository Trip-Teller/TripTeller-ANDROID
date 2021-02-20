package com.our.tripteller.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.our.tripteller.ItemDecoration
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.HomeData
import com.our.tripteller.data.RegionData
import com.our.tripteller.ui.home.detail.DetailActivity
import com.our.tripteller.ui.home.filter.FilterActivity
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    lateinit var regionAdapter: RegionAdapter
    lateinit var homeAdapter: HomeAdapter
    val regionData = mutableListOf<RegionData>()
    val homeData = mutableListOf<HomeData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regionAdapter = RegionAdapter(view.context)

        setHasOptionsMenu(true)
        rv_region.adapter = regionAdapter
        rv_region.addItemDecoration(ItemDecoration(this.context!!, 8,0))
        loadRegionDatas()

        homeAdapter = HomeAdapter(view.context) { HomeData, view: View ->
            var intent = Intent(activity as MainActivity, DetailActivity::class.java)
            intent.putExtra("idx", HomeData.id)
            startActivity(intent)
        }
        rv_home.adapter = homeAdapter
        rv_home.offscreenPageLimit = 3
        rv_home.clipToPadding = false
        rv_home.clipChildren = false
        rv_home.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(10))
        compositePageTransformer.addTransformer { view, position ->

            when {
                position <= -1.0F -> {
                    view.translationY = view.height * position
                    view.scaleX = 0.6f
                    view.scaleY = 0.6f
                    view.alpha = 0.4F
                }
                position > -1.0F && position < 0.0F -> {
                    view.translationY = view.height * -position
                    view.scaleX = 1.0f - (Math.abs(position) / 4)
                    view.scaleY = 1.0f - (Math.abs(position) / 4)
                    view.alpha = 1.0F - (Math.abs(position) / 2)
                }
                else -> {
                    view.scaleX = 1.0f
                    view.scaleY = 1.0f
                    view.alpha = 1.0F
                }
            }
        }
        rv_home.setPageTransformer(compositePageTransformer)
        rv_home.addItemDecoration(ItemDecoration(this.context!!,0,10))
        loadHomeDatas()
    }

    private fun loadHomeDatas() {
        homeData.apply {
            add (HomeData(id = 0, mainimg = "https://images.unsplash.com/photo-1516178761885-7ecb13b39255?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80", region = "서울", intro1 = "송리단길, 잠실은 이미 내", intro2 = "나와바리! 웰컴 투 잠실라빔~", tag1 = "맛집투어", tag2 = "유흥", tag3 = "커플여행", tag4 = "인생샷", tag5 = "자연", tag6 = "드라이브", cnt = 6))
            add (HomeData(id = 1, mainimg = "https://images.unsplash.com/photo-1517495306984-f84210f9daa8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80", region = "서울", intro1 = "음악을 즐기는", intro2 = "프로서울러의 가로수길 투어", tag1 = "나홀로여행", tag2 = "맛집투어", tag3 = "유흥", tag4 = "드라이브", tag5 = "나홀로여행", tag6 = null, cnt = 3))
            add (HomeData(id = 2, mainimg = "https://images.unsplash.com/photo-1595737335975-2160c924caf2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80", region = "제주도", intro1 = "최연소 해녀가 추천하는", intro2 = "제주도 바다투어 드루와 드루와", tag1 = "인생샷", tag2 = "자연", tag3 = "드라이브", tag4 = null, tag5 = null, tag6 = null, cnt = 3))
            add (HomeData(id = 3, mainimg = "https://images.unsplash.com/photo-1599025847646-58d2a1808824?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1189&q=80", region = "강원도", intro1 = "예비 한의사가 추천하는", intro2 = "구수한 원주 나들이", tag1 = "가족여행", tag2 = "자연", tag3 = "드라이브", tag4 = null, tag5 = null, tag6 = null, cnt = 3))
            add (HomeData(id = 4, mainimg = "https://images.unsplash.com/photo-1562601579-599dec564e06?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80", region = "서울", intro1 = "쇼미더머니 출신 힙합러와", intro2 = "함께하는 을지로 투어", tag1 = "유흥", tag2 = "맛집투어", tag3 = "예술", tag4 = "자연", tag5 = null, tag6 = null, cnt = 4))
        }
        homeAdapter.datas = homeData
        homeAdapter.notifyDataSetChanged()
    }

    private fun loadRegionDatas() {
        regionData.apply {
            add(RegionData(id = "전체", img = R.drawable.btn_filter_all))
            add(RegionData(id = "제주도", img = R.drawable.btn_filter_jeju))
            add(RegionData(id = "강원도", img = R.drawable.btn_filter_gangwon))
            add(RegionData(id = "부산", img = R.drawable.btn_filter_busan))
            add(RegionData(id = "경기도", img = R.drawable.btn_filter_gyounggi))
            add(RegionData(id = "서울", img = R.drawable.btn_filter_seoul))
            add(RegionData(id = "전라도", img = R.drawable.btn_filter_jeolla))
            add(RegionData(id = "경상도", img = R.drawable.btn_filter_gyeongsang))
            add(RegionData(id = "충청도", img = R.drawable.btn_filter_chungcheong))
        }
        regionAdapter.datas = regionData
        regionAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {
                val intent = Intent(context, FilterActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}