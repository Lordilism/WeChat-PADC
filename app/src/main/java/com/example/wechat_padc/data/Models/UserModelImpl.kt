package com.example.wechat_padc.data.Models

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import com.example.wechat_padc.data.VO.*
import com.example.wechat_padc.network.FireStoreApi
import com.example.wechat_padc.network.FireStoreDatabaseImpl
import com.example.wechat_padc.network.RealTimeAPi
import com.example.wechat_padc.network.RealTimeDatabaseImpl
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix

object UserModelImpl : UserModel {
    override var mFirestoreApi: FireStoreApi = FireStoreDatabaseImpl
    private var mRealtimeApi: RealTimeAPi = RealTimeDatabaseImpl


    override fun addUser(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String
    ) {
        mFirestoreApi.addUser(
            email = email,
            password,
            userName,
            dateOfBirth,
            gender,
            userUID,
            profile
        )
    }

    override fun addMoment(
        currentUser: UserVO,
        mSelectedPhotoList: MutableList<String>,
        currentTimeMillis: Long,
        content: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

//        val builder = StringBuilder()
        val photoString = mSelectedPhotoList.joinToString(", ")

        mFirestoreApi.addMoments(
            currentUser,
            photoString,
            currentTimeMillis,
            content,
            onSuccess,
            onFailure = {
                onFailure(it)
            }
        )


    }

    override fun uploadImage(
        image: Bitmap,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirestoreApi.uploadSelectedPhoto(
            imageUri = image,
            onSuccess = {
                onSuccess(it)
            }
        ) {
            onFailure(it)
        }
    }

    override fun getFeed(onSuccess: (List<MomentsVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirestoreApi.getFeed(
            onSuccess,
            onFailure
        )

    }

    @Throws(WriterException::class)
    override fun generateQRcode(
        userUID: String,
        onSuccess: (Bitmap) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                userUID,
                BarcodeFormat.QR_CODE, 500, 500, null
            )
        } catch (exception: IllegalArgumentException) {
            return onFailure(exception.localizedMessage ?: "")
        }
        val bitMatrixWidth = bitMatrix.width
        val bitMatrixHeight = bitMatrix.height
        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)
        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth
            for (x in 0 until bitMatrixWidth) {
                pixels[offset + x] = if (bitMatrix[x, y]) BLACK else WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444)
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight)
        onSuccess(bitmap)

    }

    override fun addNewContact(
        userUIDScanner: String,
        userUIDProvider: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        mFirestoreApi.addNewContact(userUIDScanner, userUIDProvider,
            onSuccess = {
                onSuccess()
            },
            onFailure = {
                onFailure(it)
            })


    }

    override fun getContactsList(
        userUID: String,
        onSuccess: (List<ContactsVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirestoreApi.getContactsList(userUID,
            onSuccess = {
                onSuccess(it)
            }, onFailure = {
                onFailure(it)
            })

    }

    override fun sendText(
        userVO: UserVO,
        userUID: String,
        receiverUid: String,
        msg: String,
        timeMillis: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val vo = MessageVO()
        vo.userUid = userUID
        vo.name = userVO.name
        vo.profile = userVO.profile
        vo.message = msg
        vo.timeMillis = timeMillis
        mRealtimeApi.sendText(vo, receiverUid, onSuccess = {
            onSuccess()
        },
            onFailure = {
                onFailure(it)
            })


    }

    override fun getUserData(
        userUID: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirestoreApi.getUserData(
            userUID,
            onSuccess,
            onFailure
        )
    }

    override fun getMessages(
        userUID: String,
        receiverUID: String,
        onSuccess: (MutableList<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealtimeApi.getMessage(userUID, receiverUID, onSuccess, onFailure)
    }

    override fun creatGroup(
        groupName: String,
        mCurrentUserId: String,
        members: MutableList<String>,
        timeStamp: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val allMember = mutableListOf<String>()
        allMember.add(mCurrentUserId)
        allMember.addAll(members)
        mRealtimeApi.creatGroup(groupName, allMember,timeStamp, onSuccess, onFailure)
    }

    override fun getGroups(onSuccess: (List<GroupVO>) -> Unit, onFailure: (String) -> Unit) {

        mRealtimeApi.getGroup(onSuccess,onFailure)

    }

    override fun sendTextGroup(
        vo: MessageVO,
        groupID: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealtimeApi.sendTextGroup(vo,groupID,onSuccess, onFailure)
    }

    override fun getMesssagesGroup(
        groupId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealtimeApi.getMessageGroup(groupId,onSuccess,onFailure)

    }

    override fun getGroupInfo(
        groupID: String,
        onSucces: (GroupVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mRealtimeApi.getGroupInfo(groupID,onSucces,onFailure)
    }
}