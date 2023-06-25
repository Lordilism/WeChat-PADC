package com.example.wechat_padc.mvp.presenters

import android.graphics.Bitmap
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.delegates.SelectedContactsDelegate
import com.example.wechat_padc.mvp.view.NewGroupView

interface NewGroupPresenter:IBasePresenter,SelectedContactsDelegate {
    fun initView(view:NewGroupView)
    fun onTapClose()
    fun onTapCreate(
        groupLogo:String,
        groupName: String,
        mSelectedContactsList: MutableList<ContactsVO>,
        mCurrentUserId: String,
        timeStamp:Long
    )
    fun onTapTextDelete()
    fun onTapLogoImage()
    fun uploadImageFromGallery(uri: Bitmap)
}