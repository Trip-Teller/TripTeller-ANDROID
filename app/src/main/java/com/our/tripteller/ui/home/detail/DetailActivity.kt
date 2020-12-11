package com.our.tripteller.ui.home.detail

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.our.tripteller.R
import com.our.tripteller.data.HomeDetailData
import kotlinx.android.synthetic.main.activity_detail.*
import kotlin.properties.Delegates

class DetailActivity : AppCompatActivity() {

    var idx by Delegates.notNull<Int>()
    var homeDetailData = mutableListOf<HomeDetailData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        setContentView(R.layout.activity_detail)

        var detailHashtagAdapter = DetailHashtagAdapter(view.context)

        act_detail_rv_hashtag.adapter = detailHashtagAdapter
        act_detail_rv_hashtag.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        if (intent.hasExtra("idx")) {
            idx = intent.getIntExtra("idx",0)
        }

        btn_consulting.setOnClickListener{
            val intent = Intent(this, RequestActivity::class.java)
            startActivity(intent)
        }

        loadData()
        initView()
    }

    private fun loadData(){
        homeDetailData.apply {
            add (HomeDetailData(id = 0, mainimg = "https://images.unsplash.com/photo-1570515137767-8ac25a5b10fa?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1821&q=80", region = "서울", intro = "송리단길, 잠실은 이미 내\n나와바리! 웰컴 투 잠실라빔~", tag1 = "맛집투어", tag2 = "유흥", tag3 = "커플여행",
                profile = "", nickname = "홍옌옌", age = 24, gender = "여자", title = "안녕하세요, 잠실리언 홍옌옌입니다!", content = "잠실 하면 가장 먼저 무엇이 떠오르시나요?\n" +
                        "롯데타워, 석촌호수, 롯데월드... 이런 뻔할 뻔자 코스만 떠오르신다면...... 저를 믿고 따라오셔도 됩니다!\n\n" +
                        "잠실 경력만 6년! 거의 모든 맛집은 다 꿰고 있답니다. 한식, 중식, 양식, 일식, 베트남식, 외계인식... 무엇이든지 다 도와드릴게요!\n\n" +
                        "특히 썸 타고 있는 예비 커플분들, 그리고 한창 사랑을 키워가고 있는 연애 초창기 커플들.. 그저 그런 데이트 장소에 지쳐가고 있다면! 저 홍옌옌을 믿어도 좋을 거예요! ",
                dish = "송리단길의 커리 맛집, ‘인딕슬로우’을 추천드려요! 커리에 대한 남다른 프라이드가 있는 사장님과 현지인 주방장님의 콜라보는 상상이상, 가격도 착하고 첫 데이트 장소로 안성맞춤이예요!",
                cafe = "저는 ‘유달리'를 추천드려요. 편안한 소파와 함께 마실 수 있는 제철 스무디와 함께라면 로맨틱지수 350% 상승 가능하답니다!",
                place = "저는 롯데타워 전망대를 추천드려요. 남산타워보다 훨씬 더 높고 아름다운 전망대에서 사랑을 속삭여보세요. 아마 당신의 님은 쓰러질 지도..."))
            add (HomeDetailData(id = 1, mainimg = "https://images.unsplash.com/photo-1571645639045-a3d43f4cafc5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=658&q=80", region = "서울", intro = "음악을 즐기는\n프로서울러의 가로수길 투어", tag1 = "나홀로여행", tag2 = "맛집투어", tag3 = "유흥",
                profile = "", nickname = "화니니", age = 27, gender = "여자", title = "가로수길의 숨은 포인트", content = "안녕하세요, 트립텔러로 활동하고 있는 27살 여자입니다! 가로수길로 이사온지 약 1년 정도 되었어요!\n\n" +
                        "그동안 가보고 좋았던 카페와 맛집 리스트를 핸드폰에 정리해두고 친구들을 데리고 가봤는데요, \n그 중 반응이 제일 좋았던 곳들을 소개해드리겠습니다! ",
                dish = "가로수길의 찐 맛집은 맥덕스 피자입니다!\n한예슬맛집이라고 할 수 있는데,  도우가 끝내줍니당",
                cafe = "아우어 베이커리를 추천드려요! 갓 구은 빵들이 명품인 카페입니다. 초코 크루아상이 제일 유명한데, 먹으려면 일찍 가야하니 꼭! 낮에가서 도전해보세요!",
                place = "가로수길을 따라 쭉 가보면 한강공원이 나옵니다! 강북에서 바라보는 한강이 아닌 강남에서 바라보는 한강으로 뷰가 끝내줘요!"))
            add (HomeDetailData(id = 2, mainimg = "https://images.unsplash.com/photo-1595737335975-2160c924caf2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80", region = "제주도", intro = "최연소 해녀가 추천하는\n제주도 바다투어 드루와 드루와", tag1 = "인생샷", tag2 = "자연", tag3 = "드라이브",
                profile = "", nickname = "제주돌고래", age = 22, gender = "여자", title = "혼자옵서예", content = "안녕하세요, 트립텔러로 활동하고 있는 최연소 제주도 해녀입니다. 저는 할머니와 함께 어렸을 때부터 바다에 종종 나갔었는데요, 제주 바닷속 아름다움에 흥미를 느껴 13살 제주도 해녀가 되기를 결심했답니다.\n\n" +
                        "저는 보통 우도에서 활동하고 우도의 전복, 문어, 보말을 많이 따요~! 우도하면 다금바리도 빼놓을 수 없는데 저와 함께 바다내음가득한 제주도 여행 시작해보시겠어요?\n\n" +
                        "컨설팅은 처음이라 떨리지만 열심히 해보겠습니다!!! 감사드려요!",
                dish = "저는 우도의 미정오분자기찌개를 추천드려요! \n저희 할머니께서 운영하시는 식당인데, 직접 잡아온 오분자기와 전복, 문어를 넣어 끓여 기가막히답니다!",
                cafe = "우도에는 아주 특별한 카페가 있는데요, 우도의 푸른바다를 바라보며 소금아메리카노를 마실 수 있는 우도카페를 추천드려요.",
                place = "저는 소머리오름을 추천드려요! 전세계에서 가장 아름다운 노을을 보실 수 있을 거예요. 오름에 올라가서 먹는 컵라면은... 아주.... 끝내준답니다!"))
            add (HomeDetailData(id = 3, mainimg = "https://images.unsplash.com/photo-1599025847646-58d2a1808824?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1189&q=80", region = "강원도", intro = "예비 한의사가 추천하는\n구수한 원주 나들이", tag1 = "가족여행", tag2 = "자연", tag3 = "드라이브",
                profile = "", nickname = "동의보감", age = 25, gender = "남자", title = "강원도 감자 묵어봤나~", content = "안녕하세요, 강원대학교 한의대에 재학중인 대학생입니다. 원주에서 태어나 원주에서 자라다가 결국 강원대에 오게되어 계속해서 원주에 있는데요, \n\n" +
                        "다들 강원도 여행하면 바다를 생각하시겠지만, 원주는 바다가 하나도 없는 내륙지방입니다!\n그대신 산과 계곡이 많은 아주 깨끗하고 아름다운 곳이에요!\n\n색다른 강원도여행이 하고 싶다면 한번 믿도 맡겨주십쇼!",
                dish = "강원도 = 감자 라는 공식이 있죠, 특히 원주는 감자옹심이가 정말 유명한데요, 원주 중앙시장에 있는 할머니옹심이를 추천드립니다! 말캉+서걱 하고 씹히는 옹심이가 정말 일품이에요!",
                cafe = "원주의 추천카페는 태기산 밑에 있는 카페거리에 몰려있어요! 계곡옆에 위치한 머루카페는 계곡물에 물을 담글 수도 있어 가족들과 같이 오는 것을 추천드려요!",
                place = "원주의 숨은 명소! 뮤지엄 산 입니다. 뮤지엄 산은 정말 수준높은 작품들이 모여있는 곳이에요! 유럽 도슨트가 추천한 곳이랍니다!"))
            add (HomeDetailData(id = 4, mainimg = "https://images.unsplash.com/photo-1562601579-599dec564e06?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80", region = "서울", intro = "쇼미더머니 출신 힙합러와\n함께하는 을지로 투어", tag1 = "유흥", tag2 = "맛집투어", tag3 = "예술",
                profile = "", nickname = "릴타타치오", age = 29, gender = "남자", title = "힙힙힙지로", content = "안녕하세요 힙합을 사랑하는 트루 힙합러 릴타치치오입니다. 저는 언더에서 지금 힙합크루로 활동을 하고 있습니다. \n\n" +
                        "힙합도 좋아하지만 음악이라면 사실 다 좋아해서 재즈바도 자주가고 음악들으러 바에 자주가는 편이에요 \n\n특히 을지로에 숨겨진 와인바가 정말 많은데, 요즘 와인바 도장꺠기를 진행중이랍니다. 저와 함께 힙지로 즐기고 싶으신 분 연락주세요!",
                dish = "을지로의 바이브를 가장 잘 느낄 수 있는 곳은 바로 을지면옥이죠. 정말 맛있습니다. 말이 필요없음. 줄서서라도 가야함. 강추",
                cafe = "을지로에 워낙 카페가 많아서 차라리 와인바와 같이하는 곳을 소개하자면 르템플이라는 바를 소개드려요. 분위기가 미쳤",
                place = "힘지로의 숨은 명소는 감각의 제국이라고 할 수 있죠. 금욜에 저와 같이 음악에 몸을 맡겨 둠칫 하실 분 있다면 꼭 가시길 추천드려요!"))
        }
    }
    private fun initView(){
        Glide.with(this).load(homeDetailData[idx].mainimg).into(iv_mainimg)
        tv_region.text = homeDetailData[idx].region
        tv_intro.text = homeDetailData[idx].intro
        tv_nickname.text = homeDetailData[idx].nickname
        tv_age.text = homeDetailData[idx].age.toString()
        tv_gender.text = homeDetailData[idx].gender
        act_detail_txt_content_title.text = homeDetailData[idx].title
        act_detail_txt_content_content.text = homeDetailData[idx].content
        tv_dish.text = homeDetailData[idx].dish
        tv_cafe.text = homeDetailData[idx].cafe
        tv_place.text = homeDetailData[idx].place
    }

}