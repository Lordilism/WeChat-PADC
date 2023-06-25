package com.example.wechat_padc.data.VO

import java.io.Serializable

data class UserVO(
    var email:String? = "",
    var name:String? = "",
    var password:String?= "",
    var profile:String? = "",
    var gender:String? = "",
    var dateOfBirth:String?= "",
    var phoneNo:String? = ""
//    var selected: Boolean = false

):Serializable