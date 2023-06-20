package com.example.wechat_padc.mvp.presenters

import android.graphics.Bitmap
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.mvp.view.NewMomentView

interface NewMomentPresenter:IBasePresenter {
    fun initView(view: NewMomentView)
    fun onTapCreate(currentUser: UserVO,mSelectedPhotoList: MutableList<String>,currentTimeMillis:Long,content:String)
    fun onTapBtnClose()
    fun createMoments()
    fun onTapSelectPhoto()
    fun onTapTakeFromCamera()
    fun onTapTakeFromGallery()
    fun uploadImageFromGallery(imageUri: Bitmap)

}