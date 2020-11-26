package com.our.tripteller.data

class MessageData (
    val text:String,
    val text_time: String,
    val messageType:Int){
    companion object {
        const val TYPE_MY_MESSAGE = 0
        const val TYPE_FRIEND_MESSAGE = 1
    }
}