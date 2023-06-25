package com.example.wechat_padc.data.VO

data class GroupVO(
    var groupID:Long=0L,
    var groupLogo:String="",
    var groupName:String="",
    var members: MutableList<String> = mutableListOf(),
) {
}