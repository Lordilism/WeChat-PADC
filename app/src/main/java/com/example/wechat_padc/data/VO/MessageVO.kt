package com.example.wechat_padc.data.VO

data class MessageVO (
    var name:String? = "",
    var timeMillis: Long=0L,
    var userUid: String ="",
    var message:String? = "",
    var profile:String?=""
        ){

}