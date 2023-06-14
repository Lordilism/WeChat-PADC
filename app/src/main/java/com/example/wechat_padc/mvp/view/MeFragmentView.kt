package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.UserVO

interface MeFragmentView:BaseView {
    fun showEditDialog()
    fun showQrDialog()
    fun uploadImage()
    fun showUserData(userVO: UserVO)
}