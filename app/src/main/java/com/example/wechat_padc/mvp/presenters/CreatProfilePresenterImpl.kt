package com.example.wechat_padc.mvp.presenters

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.Models.AuthenticationModel
import com.example.wechat_padc.data.Models.AuthenticationModelImpl
import com.example.wechat_padc.mvp.view.CreatProfileView
import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl
import com.example.wechat_padc.utils.monthInMapping

class CreatProfilePresenterImpl : CreatProfilePresenter, ViewModel() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mFireStoreApi: FireStoreApi = FireStoreDatabaseImpl
    private var mView: CreatProfileView? = null
    override fun initView(view: CreatProfileView) {
        mView = view
    }

    override fun onTapSignUp(
        email: String,
        name: String,
        password: String,
        day: String,
        month: String,
        year: String,
        gender: String,
        userProfile: String,
        phoneNumber: String
    ) {
        val monthInNumber = monthInMapping[month]
        val dateOfBirth = "$day/${monthInNumber}/$year"
        Log.i("DOB", dateOfBirth)
        mAuthenticationModel.signUp(email = email,
            password = password,
            userName = name,
            dateOfBirth = dateOfBirth,
            gender = gender,
            userProfile = userProfile,
            phoneNumber = phoneNumber,
            onSuccess = {
                mView?.navigateToLogIn()
            },
            onFailure = {
                mView?.showError(it)
            })


    }

    override fun onTapProfileUpload() {
        mView?.openGallery()

    }

    override fun uploadImage(imageUri: Bitmap) {
        mFireStoreApi.uploadSelectedPhoto(imageUri, onSuccess = {
            mView?.bindImage(it)
        },
            onFailure = {

            })

    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}