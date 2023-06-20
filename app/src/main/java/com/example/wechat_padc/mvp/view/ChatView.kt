package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.data.VO.UserVO

interface ChatView:BaseView {
    fun clearText()
    fun showTappedUserData(userVO: UserVO)
    fun showMessages(listMessageVO: MutableList<MessageVO>)



}