package com.example.wechat_padc.network

import android.util.Log
import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.data.VO.MessageVO
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

object RealTimeDatabaseImpl : RealTimeAPi {
//    val newFirebaseDatabaseUrl = "https://wechat-padc-default-rtdb.asia-southeast1.firebasedatabase.app"
//    FirebaseDatabase.getInstance().setPersistenceEnabled(true)
//    FirebaseDatabase.getInstance().setPersistenceCacheSizeBytes(10000000) // Optional: Set cache size


    private val database: DatabaseReference = Firebase.database.reference


//    private val newdatabase = FirebaseDatabase.getInstance(newFirebaseDatabaseUrl).reference


    //    private val mFireStoreApi:FireStoreApi = FireStoreDatabaseImpl
    override fun sendText(
        vo: MessageVO,
        receiverUID: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {

        database.child("contactsAndMessages")
            .child(vo.userUid)
            .child(receiverUID)
            .child(vo.timeMillis.toString())
            .setValue(MessageVO(vo.name, vo.timeMillis, vo.userUid, vo.message, vo.profile))
            .addOnCompleteListener { task ->
                if (task.isSuccessful && task.isComplete) {
                    Log.d("task_success", "Success")
                    onSuccess()
                    database.child("contactsAndMessages")
                        .child(receiverUID)
                        .child(vo.userUid)
                        .child(vo.timeMillis.toString())
                        .setValue(
                            MessageVO(
                                vo.name,
                                vo.timeMillis,
                                vo.userUid,
                                vo.message,
                                vo.profile
                            )
                        )
                } else {
                    onFailure(task.exception?.localizedMessage ?: "Failure")
                }


            }


    }

    override fun getMessage(
        userUID: String,
        receiverUID: String,
        onSuccess: (MutableList<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        database.child("contactsAndMessages")
            .child(userUID)
            .child(receiverUID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = mutableListOf<MessageVO>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue(MessageVO::class.java)?.let {
                            messageList.add(it)
                        }
                    }
                    onSuccess(messageList)
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

            })

    }

    override fun creatGroup(
        groupLogo: String,
        groupName: String,
        members: MutableList<String>,
        timeStamp: Long,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .child(timeStamp.toString())
            .setValue(GroupVO(timeStamp,groupLogo,groupName , members))
            .addOnCompleteListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "Check Internet")
            }


    }

    override fun getGroup(onSuccess: (List<GroupVO>) -> Unit, onFailure: (String) -> Unit) {
        database.child("groups")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val groupList = mutableListOf<GroupVO>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue(GroupVO::class.java)?.let {
                            groupList.add(it)
                        }
                    }
                    onSuccess(groupList)
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

            })


    }

    override fun sendTextGroup(
        vo: MessageVO,
        groupID: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .child(groupID)
            .child("messages")
            .child(vo.timeMillis.toString())
            .setValue(vo)
            .addOnCompleteListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.message ?: "Network Failure")
            }


    }

    override fun getMessageGroup(
        groupId: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .child(groupId)
            .child("messages")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val vo = mutableListOf<MessageVO>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue(MessageVO::class.java)?.let {
                            vo.add(it)
                        }
                    }
                    onSuccess(vo)

                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

            })
    }

    override fun getGroupInfo(
        groupID: String,
        onSucces: (GroupVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groups")
            .child(groupID)
            .get()
            .addOnSuccessListener {
                it.getValue(GroupVO::class.java)?.let(onSucces)
            }
            .addOnFailureListener {
                onFailure(it.message?:"")
            }

    }

    override fun getLatestMessage(
        userUID: String,
        onSuccess: (List<String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("contactsAndMessages")
            .child(userUID)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val contactList = arrayListOf<String>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.key?.let {
                            contactList.add(it)
                        }
                        onSuccess(contactList)

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

            })
    }

    override fun getMessagesOfContacts(
        mCurrentUserUid: String,
        messagedContactID: String,
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("contactsAndMessages")
            .child(mCurrentUserUid)
            .child(messagedContactID)
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val messageList = arrayListOf<MessageVO>()
                    snapshot.children.forEach { dataSnapshot ->
                        dataSnapshot.getValue(MessageVO::class.java)?.let {
                            messageList.add(it)
                        }
                    }
                    onSuccess(messageList)
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }

            })
    }

}