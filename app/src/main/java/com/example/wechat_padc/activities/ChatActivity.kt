package com.example.wechat_padc.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.GroupMessageAdapter
import com.example.wechat_padc.adapters.MessageAdapter
import com.example.wechat_padc.data.VO.GroupVO
import com.example.wechat_padc.data.VO.MessageVO
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.databinding.ActivityChatBinding
import com.example.wechat_padc.dummy.options
import com.example.wechat_padc.mvp.presenters.ChatPresenterImpl
import com.example.wechat_padc.mvp.view.ChatView
import com.squareup.picasso.Picasso

class ChatActivity : AppCompatActivity(), ChatView {
    private lateinit var binding: ActivityChatBinding
    private lateinit var mMessageAdapter: MessageAdapter
    private lateinit var mGroupMessageAdapter: GroupMessageAdapter

    private lateinit var mPresenter: ChatPresenterImpl

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 100
        const val REQUEST_IMAGE_PICK = 101
        const val RECEIVER_UID = "RECEIVER_UID"
        const val CURRENT_UID = "CURRENT_UID"
        const val GROUP_ID = "GROUP_ID"
        const val FLAG = "FLAG"

        fun newIntent(context:Context,msgReceiver:String,currentUID:String,flag:Boolean):Intent{

            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra(RECEIVER_UID,msgReceiver)
            intent.putExtra(CURRENT_UID,currentUID)
            intent.putExtra(GROUP_ID,msgReceiver)
            intent.putExtra(FLAG,flag)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpPresenter()
        val flag = intent.getBooleanExtra(FLAG,false)
        if (flag){
            val msgReceiverUID = intent.getStringExtra(RECEIVER_UID)

            val currentUID = intent.getStringExtra(CURRENT_UID)
            setUpMessageRecyclerView(currentUID)

            setUpListeners(msgReceiverUID!!)

            mPresenter.onUIReady(this,msgReceiverUID)
        }else{
            val groupID = intent.getStringExtra(GROUP_ID)
            val currentUID = intent.getStringExtra(CURRENT_UID)
            setUpListenersForGroup(groupID!!)
            setUpMessageRecyclerView(currentUID)
            mPresenter.onUIReadyForGroup(this,groupID!!)
        }

    }

    private fun setUpRecyclerView() {
//        mGroupMessageAdapter = GroupMessageAdapter(mPresenter)
//        binding.rvChatMessages.adapter = mGroupMessageAdapter
//        binding.rvChatMessages.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

    }

    private fun setUpListenersForGroup(groupId: String) {
        binding.btnSend.setOnClickListener {
            val text = binding.etMessage.text.toString()
            mPresenter.onTapSendGroupMessage(text,System.currentTimeMillis(),groupId)
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(ChatPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpListeners(msgReceiverUID: String) {
//        setUpPickerDialog()
        binding.ivSendPicture.setOnClickListener {
            setUpPickerDialog()
        }

        binding.btnSend.setOnClickListener {
            val textMsg = binding.etMessage.text.toString()
            mPresenter.onTapTextSend(textMsg,msgReceiverUID,System.currentTimeMillis())
        }
    }

    private fun setUpPickerDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chooser")
        builder.setItems(options) { dialog, options ->
            when (options) {
                0 -> takePhoto()
                1 -> chooseFromGallery()
                2 -> dialog.dismiss()
            }

        }
        builder.create()
        builder.show()

    }

    private fun chooseFromGallery() {
        val takeFromGallery =
            Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(takeFromGallery, REQUEST_IMAGE_PICK)
    }

    private fun takePhoto() {
        val takeFromCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeFromCamera, REQUEST_IMAGE_CAPTURE)

    }

    private fun setUpMessageRecyclerView(currentUID: String?) {

        mMessageAdapter = MessageAdapter(currentUID!!)
        binding.rvChatMessages.adapter = mMessageAdapter
        binding.rvChatMessages.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                // Handle the captured image

            }
            REQUEST_IMAGE_PICK -> {
                val imageUri = data?.data
                // Handle the selected image
            }
        }


    }


    override fun clearText() {
        binding.etMessage.text.clear()
    }

    override fun showTappedUserData(userVO: UserVO) {
        binding.tvFriendName.text = userVO.name
        Picasso.get().load(userVO.profile).resize(150,150).into(binding.ivFriendProfile)
    }

    override fun showMessages(listMessageVO: MutableList<MessageVO>) {
        mMessageAdapter.setNewData(listMessageVO)

    }

    override fun showGroupInfo(groupVO: GroupVO) {
        Picasso.get()
            .load(groupVO.groupLogo)
            .into(binding.ivFriendProfile)
        binding.tvFriendName.text = groupVO.groupName
    }


    override fun showError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }


}