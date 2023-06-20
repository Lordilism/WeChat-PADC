package com.example.wechat_padc.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthManagerImpl : AuthManager {
    //firebase auth
    private val mFireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val mFireStoreApi: FireStoreApi = FireStoreDatabaseImpl


    override fun signUpWithEmail(
        email: String,
        password: String,
        userName: String,
        dateOfBirth: String,
        gender: String,
        userProfile: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {


        mFireBaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                mFireBaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(userName).build()
                )
                val user = Firebase.auth.currentUser
                val userUID = user?.uid?:""
                mFireStoreApi.addUser(
                    email = email,
                    password = password,
                    userName = userName,
                    dateOfBirth = dateOfBirth,
                    gender = gender,
                    userUID = userUID,
                    profile = userProfile
                )
                onSuccess()
//                Log.d("test" , "$email $password $userName $dateOfBirth $gender ${userUID}}")


            }else{
                onFailure(it.exception?.message?: "Failure")
            }

        }
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFireBaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete){
                onSuccess()
            }else{
                onFailure(it.exception?.message?: "Failed")
            }
        }

    }

    override fun getUserUid(): String {
        return Firebase.auth.currentUser?.uid.toString()


    }


}