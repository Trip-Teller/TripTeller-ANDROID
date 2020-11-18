package com.our.tripteller.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.our.tripteller.ItemDecoration
import com.our.tripteller.MainActivity
import com.our.tripteller.R
import com.our.tripteller.data.HomeData
import com.our.tripteller.data.RegionData
import com.our.tripteller.ui.home.Detail.DetailActivity
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
        rv_region.adapter = regionAdapter
        rv_region.addItemDecoration(ItemDecoration(this.context!!, 8,0))
        loadRegionDatas()

        homeAdapter = HomeAdapter(view.context) { view: View ->
            var intent = Intent(activity as MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }
        rv_home.adapter = homeAdapter
        rv_home.offscreenPageLimit = 2
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
            add (
                HomeData(
                    mainimg = "https://images.unsplash.com/photo-1525183995014-bd94c0750cd5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80",
                    region = "부산",
                    intro = "해운대 24년 거주 부산 토박이의\n맛집 여행기 들어보실래요?",
                    tag = "#커플여행"
                )
            )
            add (
                HomeData(
                    mainimg = "https://images.unsplash.com/photo-1571645639045-a3d43f4cafc5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=658&q=80",
                    region = "제주도",
                    intro = "해운대 24년 거주 부산 토박이의\n맛집 여행기 들어보실래요?",
                    tag = "#커플여행"
                )
            )
            add (
                HomeData(
                    mainimg = "https://images.unsplash.com/photo-1507041957456-9c397ce39c97?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    region = "강원도",
                    intro = "해운대 24년 거주 부산 토박이의\n맛집 여행기 들어보실래요?",
                    tag = "#커플여행"
                )
            )
            add (
                HomeData(
                    mainimg = "https://images.unsplash.com/photo-1489549132488-d00b7eee80f1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80",
                    region = "서울",
                    intro = "해운대 24년 거주 부산 토박이의\n맛집 여행기 들어보실래요?",
                    tag = "#커플여행"
                )
            )
            add (
                HomeData(
                    mainimg = "https://images.unsplash.com/photo-1549194400-06e6874c2fd1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=700&q=80",
                    region = "부산",
                    intro = "해운대 24년 거주 부산 토박이의\n맛집 여행기 들어보실래요?",
                    tag = "#커플여행"
                )
            )
        }
        homeAdapter.datas = homeData
        homeAdapter.notifyDataSetChanged()
    }

    private fun loadRegionDatas() {
        regionData.apply {
            add (
                RegionData(
                    id = "전체",
                    img = R.drawable.btn_filter_all
                )
            )
            add (
                RegionData(
                    id = "제주도",
                    img = R.drawable.btn_filter_jeju
                )
            )
            add (
                RegionData(
                    id = "강원도",
                    img = R.drawable.btn_filter_gangwon
                )
            )
            add (
                RegionData(
                    id = "부산",
                    img = R.drawable.btn_filter_busan
                )
            )
            add (
                RegionData(
                    id = "경기도",
                    img = R.drawable.btn_filter_gyounggi
                )
            )
            add (
                RegionData(
                    id = "서울",
                    img = R.drawable.btn_filter_seoul
                )
            )
            add (
                RegionData(
                    id = "전라도",
                    img = R.drawable.btn_filter_jeolla
                )
            )
            add (
                RegionData(
                    id = "경상도",
                    img = R.drawable.btn_filter_gyeongsang
                )
            )
            add (
                RegionData(
                    id = "충청도",
                    img = R.drawable.btn_filter_chungcheong
                )
            )
        }
        regionAdapter.datas = regionData
        regionAdapter.notifyDataSetChanged()
    }

}