package com.example.wechat_padc.mvp.view

import com.example.wechat_padc.data.VO.UserVO

interface NewMomentView:BaseView {
    fun closeMomentCreate()
    fun showUserData(currentUser: UserVO)
    fun showDialogForPhoto()
    fun takePhotoFromCamera()
    fun takePhotoFromGallery()
    fun showUserSelectedPhoto(imageLink: String)
    fun createMomentComplete()
}