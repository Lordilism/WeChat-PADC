package com.example.wechat_padc.data.VO

import java.io.Serializable

data class ContactsVO(
    var name: String? = "" ,
    var profile: String?="",
    var gender: String?="",
    var email:String?="",
    var dateOfBirth:String?="",
    var userUID:String?= "",
    var isSelected:Boolean?= false

):Serializable {
}