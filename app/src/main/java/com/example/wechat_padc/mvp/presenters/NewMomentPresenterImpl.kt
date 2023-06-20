package com.example.wechat_padc.mvp.presenters

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.data.Models.UserModel
import com.example.wechat_padc.data.Models.UserModelImpl
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.mvp.view.NewMomentView
import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl

class NewMomentPresenterImpl : NewMomentPresenter, ViewModel() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    private val mUserModel: UserModel = UserModelImpl

    private var mChosenImageForFileUpload: MomentsVO? = null

    private val mFireStoreApi: FireStoreApi = FireStoreDatabaseImpl

    private var mView: NewMomentView? = null
    override fun initView(view: NewMomentView) {
        mView = view
    }

    override fun onTapCreate(
        currentUser: UserVO,
        mSelectedPhotoList: MutableList<String>,
        currentTimeMillis: Long,
        content: String
    ) {
        mUserModel.addMoment(currentUser,mSelectedPhotoList,currentTimeMillis,content,
        onSuccess = {
            mView?.createMomentComplete()
        },
        onFailure = {
            mView?.showError(it)
        })

    }

    override fun onTapBtnClose() {
        mView?.closeMomentCreate()

    }


    override fun createMoments() {

    }

    override fun onTapSelectPhoto() {
        mView?.showDialogForPhoto()
    }

    override fun onTapTakeFromCamera() {
        mView?.takePhotoFromCamera()

    }

    override fun onTapTakeFromGallery() {
        mView?.takePhotoFromGallery()
    }

    override fun uploadImageFromGallery(
        imageUri: Bitmap
    ) {
        mUserModel.uploadImage(imageUri,
        onSuccess = {
            mView?.showUserSelectedPhoto(it)
        }) {
            mView?.showError(it)
        }
    }


    override fun onUiReady(owner: LifecycleOwner) {

        val userUID = mAuthenticationModel.getUserUID()

        mFireStoreApi.getUserData(userUID, onSuccess = {
            mView?.showUserData(it)
        }, onFailure = {
            mView?.showError(it)
        })





    }
}