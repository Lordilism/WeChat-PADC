package com.example.wechat_padc.data.VO

import java.io.Serializable

data class GroupVO(
    var groupName:String="",
    var groupID:Long=0L,
    var members: MutableList<String> = mutableListOf(),
):Serializable {
}