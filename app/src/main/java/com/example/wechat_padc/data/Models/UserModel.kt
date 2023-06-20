package com.example.wechat_padc.data.Models

import android.graphics.Bitmap
import com.example.wechat_padc.data.VO.*
import com.example.wechat_padc.network.FireStoreApi

interface UserModel {
    var mFirestoreApi: FireStoreApi

    fun addUser(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String
    )

    fun addMoment(
        currentUser: UserVO,
        mSelectedPhotoList: MutableList<String>,
        currentTimeMillis: Long,
        content: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun uploadImage(image: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)
    fun getFeed(onSuccess: (List<MomentsVO>) -> Unit, onFailure: (String) -> Unit)
    fun generateQRcode(userUID: String, onSuccess: (Bitmap) -> Unit, onFailure: (String) -> Unit)
    fun addNewContact(
        userUIDScanner: String,
        userUIDProvider: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getContactsList(
        userUID: String,
        onSuccess: (List<ContactsVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun sendText(
        userVO: UserVO,
        userUID: String,
        receiverUid: String,
        msg: String,
        timeMillis: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserData(userUID: String, onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit)

    fun getMessages(userUID: String,receiverUID:String,onSuccess:(MutableList<MessageVO>)->Unit, onFailure: (String)->Unit)
    fun creatGroup(groupName: String, mCurrentUserId: String, members: MutableList<String>,timeStamp:Long,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getGroups(onSuccess:(List<GroupVO>)->Unit,onFailure: (String) -> Unit)
    fun sendTextGroup(vo: MessageVO, groupID: String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getMesssagesGroup(groupId:String,onSuccess: (List<MessageVO>) -> Unit, onFailure: (String) -> Unit)
    fun getGroupInfo(groupID: String, onSucces: (GroupVO) -> Unit, onFailure: (String) -> Unit)
}