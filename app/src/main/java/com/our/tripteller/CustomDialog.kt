package com.our.tripteller

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Button
import kotlinx.android.synthetic.main.popup_custom.*

class CustomDialog(context : Context){

    private val dlg = Dialog(context)
    private lateinit var btnOk : Button
    private lateinit var btnCancel : Button
    private lateinit var listener : MyDialogOKClickedListener

    fun start(img: Int, content: String, okText: String, cancelText: String) {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.popup_custom)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //색 투명하게

        if(img == 0){
            dlg.iv_icon.visibility = View.GONE
        }
        else {
            dlg.iv_icon.setImageResource(img)
        }
        dlg.tv_content.text = content
        dlg.btn_ok.text = okText
        dlg.btn_cancel.text = cancelText

        btnOk = dlg.findViewById(R.id.btn_ok)
        btnOk.setOnClickListener {
            listener.onOKClicked(false)
            dlg.dismiss()
        }

        btnCancel = dlg.findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }

    fun setOnOKClickedListener(listener: (Boolean) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: Boolean) {
                listener(content)
            }
        }
    }


    interface MyDialogOKClickedListener {
        fun onOKClicked(content : Boolean)
    }

}