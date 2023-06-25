package com.example.wechat_padc.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.mvp.view.NewGroupView

class NewGroupPresenterImpl:NewGroupPresenter, ViewModel() {
    private var mView: NewGroupView? = null
    private val mAuthenticationModel:AuthenticationModel  = AuthenticationModelImpl
    private val mUserModel:UserModel = UserModelImpl

    override fun initView(view: NewGroupView) {
        mView = view
    }

    override fun onTapClose() {
        mView?.cancelGroupCreation()

    }

    override fun onTapCreate(
        groupLogo: String,
        groupName: String,
        mSelectedContactsList: MutableList<ContactsVO>,
        mCurrentUserId: String,
        timeStamp: Long
    ) {
        val members = mutableListOf<String>()
        mSelectedContactsList.forEach {
            members.add(it.userUID!!)
        }

        mUserModel.creatGroup(groupLogo,groupName,mCurrentUserId,members,timeStamp,
        onSuccess = {
           mView?.createGroup()
        },
        onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapTextDelete() {
       mView?.deleteAllTextFromField()
    }

    override fun onTapLogoImage() {
        mView?.openGallery()
    }

    override fun uploadImageFromGallery(uri: Bitmap) {

        mUserModel.uploadImage(uri, onSuccess = {
            mView?.showGroupLogo(it)
        },
        onFailure = {
            mView?.showError(it)
        })

    }

    override fun onUiReady(owner: LifecycleOwner) {
        val userUid = mAuthenticationModel.getUserUID()

        mView?.showUserData(userUid)

        mUserModel.getContactsList(userUid,
        onSuccess = {
            mView?.showContactsList(it)
        },
        onFailure = {
            mView?.showError(it)
        })

    }

    override fun onTapContacts(userUID: ContactsVO) {
        mView?.showSelectedContactList(userUID)

    }


}