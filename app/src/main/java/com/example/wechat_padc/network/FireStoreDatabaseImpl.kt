package com.example.wechat_padc.network

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


object FireStoreDatabaseImpl:FireStoreApi {
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
}