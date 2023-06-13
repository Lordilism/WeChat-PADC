package com.example.wechat_padc.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.wechat_padc.data.VO.Models.AuthenticationModel
import com.example.wechat_padc.data.VO.Models.AuthenticationModelImpl
import com.example.wechat_padc.mvp.view.CreatProfileView

class CreatProfilePresenterImpl : CreatProfilePresenter, ViewModel() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
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
        gender: String
    ) {

        val dateOfBirth = "$day/$month/$year"
        Log.i("DOB",dateOfBirth)
        mAuthenticationModel.signUp(email = email,
            password = password,
            userName = name,
            dateOfBirth = dateOfBirth,
            gender = gender,
            onSuccess = {
                mView?.navigateToLogIn()
            },
            onFailure = {
                mView?.showError(it)
            })


    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}