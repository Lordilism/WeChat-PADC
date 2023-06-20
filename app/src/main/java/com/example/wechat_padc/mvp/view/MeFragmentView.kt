package com.example.wechat_padc.mvp.view

import android.graphics.Bitmap
import com.example.wechat_padc.data.VO.UserVO

interface MeFragmentView:BaseView {
    fun showEditDialog()
    fun showQrDialog()
    fun uploadImage()
    fun showUserData(userVO: UserVO)
    fun bindQrtoView(bitmat: Bitmap)
}