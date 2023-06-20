package com.example.wechat_padc.data.VO

import java.io.Serializable

data class MomentsVO(
    var millis: Long?=0L,
    var content: String?="",
    var userName: String?="",
    var photoList: String="",
    var userProfile:String=""
):Serializable