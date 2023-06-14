package com.example.wechat_padc.network

import android.util.Log
import com.example.wechat_padc.data.VO.UserVO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


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
        profile: String
    ) {
        val map = hashMapOf(
            "userUID" to userUID,
            "name" to userName,
            "email" to email,
            "password" to password,
            "dateOfBirth" to dateOfBirth,
            "gender" to gender,
            "profile" to profile

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

                    onSuccess(user)
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage?: "Not Found" )
            }
    }
}