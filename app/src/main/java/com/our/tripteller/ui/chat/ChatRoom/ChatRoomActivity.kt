package com.our.tripteller.ui.chat.ChatRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.our.tripteller.R
import com.our.tripteller.data.MessageData
import kotlinx.android.synthetic.main.activity_chat_room.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class ChatRoomActivity : AppCompatActivity() {

    companion object {
        private var mHandler: Handler? = null
        var socket = Socket()
        lateinit var writeSocket: DataOutputStream
        lateinit var readSocket: DataInputStream
        private val ip = "192.168.1.123" // IP 번호
        private val port = 8888 // port 번호
        var data = ""
        val messageData = mutableListOf<MessageData>()

        lateinit var chatAdapter: ChatAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        connect()

//        myChatAdapter = MyChatAdapter(this)
//        youChatAdapter = YouChatAdapter(this)

        chatAdapter = ChatAdapter(messageData)

//        rv_msg.adapter = myChatAdapter
        rv_msg.adapter = chatAdapter
        rv_msg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_msg.smoothScrollToPosition(chatAdapter.itemCount)

        edit_msg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_send.isEnabled = !s.isNullOrBlank()
            }
        })

        btn_send.setOnClickListener {
            //end_msg(edit_msg.text)
            val thread = SendThread(edit_msg.text)
            thread.start()
        }

        Thread.currentThread()

    }

//    override fun onClick(v: View) {
//        when (v.id) {
//            R.id.connect_btn -> connect()
//        }
//    }

    // 로그인 정보 db에 넣어주고 연결시켜야 함.
    private fun connect() {
        mHandler = Handler()
        Log.w("connect", "연결 하는중")
        // 받아오는거
        val checkUpdate: Thread = object : Thread() {
            override fun run() {
                // 서버 접속
                try {
                    socket = Socket(ip, port)
                    Log.w("서버 접속됨", "서버 접속됨")
                } catch (e1: IOException) {
                    Log.w("서버접속못함", "서버접속못함")
                    e1.printStackTrace()
                }

                // Buffered가 잘못된듯.
                try {
//                    dos = DataOutputStream(socket?.getOutputStream()) // output에 보낼꺼 넣음
//                    dis = DataInputStream(socket?.getInputStream()) // input에 받을꺼 넣어짐
//                    dos!!.writeUTF("안드로이드에서 서버로 연결요청")

                    writeSocket = DataOutputStream(socket.getOutputStream())
                    readSocket = DataInputStream(socket.getInputStream())
//                    var msg = "안드로이드에서 서버로 연결요청"
//                    writeSocket.write(msg.toByteArray())    //메시지 전송 명령 전송

                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.w("버퍼", "버퍼생성 잘못됨")
                }

                Log.w("버퍼", "버퍼생성 잘됨")

                while (true) {
                    // 서버에서 받아옴
                    try {
                        while (true) {

                            var dataArr = ByteArray(1024) // 1024만큼 데이터 받기
                            readSocket.read(dataArr) // byte array에 데이터를 씁니다.
                            data = String(dataArr)

                            if (data == "99") {
                                socket.close()
                                break
                            }

                            messageData.apply {
                                add(
                                    MessageData(
                                        text = data,
                                        text_time = "오후 7:34",
                                        messageType = 1
                                    )
                                )
                            }

                            chatAdapter.data = messageData
                            chatAdapter.notifyDataSetChanged()

                        }
                    } catch (e: Exception) {
                    }
                }
            }
        }
        // 소켓 접속 시도, 버퍼생성
        checkUpdate.start()
    }

    private fun send_msg(text: Editable) {

    }

    internal class SendThread(text: Editable) : Thread() {

        private var text = text.toString()
        override fun run() {
            //서버 실행
            try {
                writeSocket = DataOutputStream(socket.getOutputStream())

                writeSocket.write(text.toByteArray())
                //writeSocket.flush()

                messageData.apply {
                    add(
                        MessageData(
                            text = text,
                            text_time = "오후 7:44",
                            messageType = 0
                        )
                    )
                }

                chatAdapter.data = messageData
                chatAdapter.notifyDataSetChanged()

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

}
