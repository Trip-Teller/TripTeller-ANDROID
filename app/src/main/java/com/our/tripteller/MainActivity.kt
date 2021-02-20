package com.our.tripteller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.our.tripteller.ui.chat.ChatFragment
import com.our.tripteller.ui.home.MainFragment
import com.our.tripteller.ui.mypage.MypageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.view.*


class MainActivity : AppCompatActivity() {

    val FINISH_INTERVAL_TIME : Long = 2000
    var backPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowCustomEnabled(true)  // custom하기 위해
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        supportFragmentManager.beginTransaction().add(R.id.main_view, MainFragment()).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_view , MainFragment()).commitAllowingStateLoss()
                    tv_home.visibility = View.VISIBLE
                    tv_chat.visibility = View.GONE
                }
                R.id.menu_chatting -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_view , ChatFragment()).commitAllowingStateLoss()
                    tv_home.visibility = View.GONE
                    tv_chat.visibility = View.VISIBLE
                }
                R.id.menu_mypage -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_view , MypageFragment()).commitAllowingStateLoss()
                    tv_home.visibility = View.GONE
                    tv_chat.visibility = View.GONE
                }
            }
            true
        }

        btn_terms.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            val customDialog = CustomDialog(this)
            customDialog.setOnOKClickedListener {
                val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val layout = inflater.inflate(R.layout.custom_toast, null)

                with(Toast(this)) {
                    setGravity(Gravity.CENTER, 0, 0)
                    duration = Toast.LENGTH_SHORT
                    view = layout
                    view.toast_message.text = "로그아웃이 완료되었습니다."
                    show()
                }
            }
            customDialog.start("정말 로그아웃 하시겠어요?","네, 할게요.", "아뇨, 안 해요!")
        }

        btn_withdrawal.setOnClickListener {
            val customDialog = CustomDialog(this)
            customDialog.setOnOKClickedListener {
                val customDialog = CustomDialog(this)
                customDialog.setOnOKClickedListener {
                    val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val layout = inflater.inflate(R.layout.custom_toast, null)

                    with(Toast(this)) {
                        setGravity(Gravity.CENTER, 0, 0)
                        duration = Toast.LENGTH_SHORT
                        view = layout
                        view.toast_message.text = "회원탈퇴가 완료되었습니다."
                        show()
                    }
                }
                customDialog.start("저는 헤어지기 싫어요..\n다시 한 번 더 물어봐도 될까요?","정말 안녕...", "계속 함께할래요!")
            }
            customDialog.start("정말 탈퇴하시겠어요?\n보여드릴 재미있는 서비스가 많아요\uD83D\uDE22","네, 할게요.", "아뇨, 안 해요!")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            super.onBackPressed()
        }
        else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }
}