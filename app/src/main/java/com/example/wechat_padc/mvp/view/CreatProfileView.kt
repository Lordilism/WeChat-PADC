package com.example.wechat_padc.mvp.view

interface CreatProfileView:BaseView {

    fun navigateToLogIn()
    fun openGallery()
    fun bindImage(url: String)

}