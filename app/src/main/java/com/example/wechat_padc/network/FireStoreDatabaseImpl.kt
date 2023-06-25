package com.example.wechat_padc.network

import android.graphics.Bitmap
import android.util.Log
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.data.VO.MomentsVO
import com.example.wechat_padc.data.VO.UserVO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*


object FireStoreDatabaseImpl : FireStoreApi {
    val dB = Firebase.firestore

    private val storage = FirebaseStorage.getInstance()    // firebase storage
    private val storageReference = storage.reference
    override fun addUser(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userUID: String,
        profile: String,
        phoneNumber: String
    ) {
        val map = hashMapOf(
            "userUID" to userUID,
            "name" to userName,
            "email" to email,
            "password" to password,
            "dateOfBirth" to dateOfBirth,
            "gender" to gender,
            "profile" to profile,
            "phoneNo" to phoneNumber

        )

        dB.collection("users")
            .document(userUID)
            .set(map)
            .addOnSuccessListener { Log.d("Success", "Successfully add user to database") }
            .addOnFailureListener { Log.d("Failed", "User Creation Failed") }
    }

    override fun getUserData(
        userUID: String,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dB.collection("users")
            .document(userUID)
            .get()
            .addOnSuccessListener { snapShot ->

                val data = snapShot.data
                val user = UserVO()
                user.name = data?.get("name") as String
                user.email = data["email"] as String
                user.gender = data["gender"] as String
                user.dateOfBirth = data["dateOfBirth"] as String
                user.password = data["password"] as String
                user.profile = data["profile"] as String
                user.phoneNo = data["phoneNo"] as String

                onSuccess(user)
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "Not Found")
            }
    }

    override fun addMoments(
        currentUser: UserVO,
        mSelectedPhotoList: String,
        currentTimeMillis: Long,
        content: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val map = hashMapOf(
            "username" to currentUser.name,
            "content" to content,
            "selectionPhoto" to mSelectedPhotoList,
            "timeMillis" to currentTimeMillis,
            "userProfile" to currentUser.profile

        )

        dB.collection("moments")
            .document(currentTimeMillis.toString())
            .set(map)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "")
            }


    }

    override fun uploadSelectedPhoto(
        imageUri: Bitmap,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        val baos = ByteArrayOutputStream()
        imageUri.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            it.localizedMessage?.let { message -> onFailure(message.toString()) }
            //
        }.addOnSuccessListener { taskSnapshot ->
            //
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val imageUrl = task.result?.toString()

            imageUrl?.let(onSuccess)
        }


    }

    override fun getFeed(onSuccess: (List<MomentsVO>) -> Unit, onFailure: (String) -> Unit) {
        dB.collection("moments")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "")
                } ?: run {
                    val momentListVO = mutableListOf<MomentsVO>()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        val moment = MomentsVO()
                        moment.userName = data?.get("username") as String
                        moment.photoList = data.get("selectionPhoto") as String
                        moment.content = data.get("content") as String
                        moment.millis = data.get("timeMillis") as Long
                        moment.userProfile = data["userProfile"] as String
                        momentListVO.add(moment)

                    }
                   val reversedList = momentListVO.reversed()
                    onSuccess(reversedList)
                }
            }

    }

    override fun addNewContact(
        userUIDScanner: String,
        userUIDProvider: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {


        getUserData(userUIDProvider,
            onSuccess = {
                val map = hashMapOf(
                    "name" to it.name,
                    "profile" to it.profile,
                    "gender" to it.gender,
                    "dateOfBirth" to it.dateOfBirth,
                    "email" to it.email,
                    "userUID" to userUIDProvider
                )
                dB.collection("users")
                    .document(userUIDScanner)
                    .collection("contacts")
                    .document(userUIDProvider)
                    .set(map)
                    .addOnSuccessListener {
                        getUserData(
                            userUIDScanner, onSuccess = {
                                val mapOfScanner = hashMapOf(
                                    "name" to it.name,
                                    "profile" to it.profile,
                                    "gender" to it.gender,
                                    "dateOfBirth" to it.dateOfBirth,
                                    "email" to it.email,
                                    "userUID" to userUIDScanner
                                )

                                dB.collection("users")
                                    .document(userUIDProvider)
                                    .collection("contacts")
                                    .document(userUIDScanner)
                                    .set(mapOfScanner)
                                    .addOnSuccessListener { onSuccess() }
                                    .addOnFailureListener { onFailure(it.localizedMessage ?: "") }


                            },
                            onFailure = {
                                onFailure(it)
                            }
                        )
                    }
                    .addOnFailureListener {
                        onFailure(it.localizedMessage ?: "")
                    }


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

        dB.collection("users")
            .document(userUID)
            .collection("contacts")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "")
                } ?: run {
                    val listVO = mutableListOf<ContactsVO>()
                    val result = value?.documents ?: arrayListOf()
                    for (i in result) {
                        val vo = ContactsVO()
                        val data = i.data
                        vo.name = data?.get("name") as String
                        vo.email = data.get("email") as String
                        vo.gender = data.get("gender") as String
                        vo.profile = data.get("profile") as String
                        vo.dateOfBirth = data.get("dateOfBirth") as String
                        vo.userUID = data.get("userUID") as String
                        listVO.add(vo)

                    }
                    onSuccess(listVO)
                }
            }
    }
}
