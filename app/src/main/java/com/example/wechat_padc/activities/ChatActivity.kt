package com.example.wechat_padc.activities

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.MessageAdapter
import com.example.wechat_padc.databinding.ActivityChatBinding
import com.example.wechat_padc.dummy.anotherUserDummyMessage
import com.example.wechat_padc.dummy.currentUserDummyMessage
import com.example.wechat_padc.dummy.options

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var mMessageAdapter: MessageAdapter

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 100
        const val REQUEST_IMAGE_PICK = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpMessageRecyclerView()

        setUpListeners()


    }

    private fun setUpListeners() {
//        setUpPickerDialog()
        binding.ivSendPicture.setOnClickListener {
            setUpPickerDialog()
        }
    }

    private fun setUpPickerDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ဘာလဲကွာ")
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

    private fun setUpMessageRecyclerView() {
        mMessageAdapter = MessageAdapter()
        mMessageAdapter.setNewData(currentUserDummyMessage)
        mMessageAdapter.setNewDataForAnother(anotherUserDummyMessage)

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

    override fun onRestart() {
        super.onRestart()

    }


}