package com.example.wechat_padc.network

import android.graphics.Bitmap
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.data.VO.UserVO

interface FireStoreApi {
    fun addUser(
        email:String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String,
        phoneNumber:String
    )

    fun getUserData(userUID:String, onSuccess:(UserVO)-> Unit, onFailure:(String)->Unit )

    fun addMoments(
        currentUser: UserVO,
        mSelectedPhotoList: String,
        currentTimeMillis: Long,
        content: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
    fun uploadSelectedPhoto(
        imageUri: Bitmap,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit)

    fun getFeed(onSuccess: (List<MomentsVO>) -> Unit,onFailure: (String) -> Unit)

    fun addNewContact(userUIDScanner:String, userUIDProvider: String, onSuccess:()->Unit, onFailure: (String) -> Unit)

    fun getContactsList(userUID: String,onSuccess: (List<ContactsVO>) -> Unit,onFailure: (String) -> Unit)

}