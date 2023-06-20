package com.example.wechat_padc.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.SelectedPhotoAdapter
import com.example.wechat_padc.data.VO.UserVO
import com.example.wechat_padc.databinding.ActivityNewMomentBinding
import com.example.wechat_padc.dummy.options
import com.example.wechat_padc.mvp.presenters.NewMomentPresenterImpl
import com.example.wechat_padc.mvp.view.NewMomentView
import com.squareup.picasso.Picasso

class NewMomentActivity : BaseActivity(), NewMomentView {
    private lateinit var binding: ActivityNewMomentBinding
    private lateinit var mPresenter: NewMomentPresenterImpl
    private lateinit var mSelectedPh0toAdapter: SelectedPhotoAdapter

//    private var mSelctedImageUri = arrayListOf<Uri>()

    private var mSelectedPhotoList = mutableListOf<String>()

    private var mCurrentUserVO: UserVO? = null



    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_PICK = 2
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, NewMomentActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMomentBinding.inflate(layoutInflater)
        val view = binding.root

        //get User info


        setContentView(view)
        setUpAdapter()
        setUpPresenter()
        setUpAdapter()
        setUpListerners()

        mPresenter.onUiReady(this)


    }

    private fun setUpAdapter() {
        mSelectedPh0toAdapter = SelectedPhotoAdapter(this)
        binding.rvSelectedPhotos.adapter = mSelectedPh0toAdapter
        binding.rvSelectedPhotos.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun setUpListerners() {

        binding.btnCreate.setOnClickListener {
            if (binding.etCreateMoment.text.toString().trim().isNotEmpty()) {
                val content = binding.etCreateMoment.text.toString()

                mCurrentUserVO?.let { mPresenter.onTapCreate(it,mSelectedPhotoList,System.currentTimeMillis(),content) }

            } else {
                Toast.makeText(this, "Write SomeThings", Toast.LENGTH_SHORT).show()
            }

        }


        //Cancel Creation
        binding.btnClose.setOnClickListener {
            closeMomentCreate()
        }

        binding.btnSelectPhoto.setOnClickListener {
            mPresenter.onTapSelectPhoto()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(NewMomentPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    override fun closeMomentCreate() {
        super.onBackPressed()
    }

    override fun showUserData(currentUser: UserVO) {
        mCurrentUserVO = currentUser
        binding.tvUserNameNewMoments.text = currentUser.name
        Picasso.get()
            .load(mCurrentUserVO!!.profile)
            .resize(200,200)
            .into(binding.ivProfileNewMoments)


    }

    override fun showDialogForPhoto() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Which One")
        builder.setItems(options) { dialog, item ->
            when (item) {
                0 -> mPresenter.onTapTakeFromCamera()
                1 -> mPresenter.onTapTakeFromGallery()
                2 -> dialog.dismiss()
            }

        }
        builder.show()


    }

    override fun takePhotoFromCamera() {
        val takeFromCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeFromCamera, REQUEST_IMAGE_CAPTURE)

    }

    override fun takePhotoFromGallery() {
        val takeFromGallery =
            Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(takeFromGallery, REQUEST_IMAGE_PICK)

    }

    override fun showUserSelectedPhoto(imageLink: String) {
        mSelectedPhotoList.add(imageLink)
        mSelectedPhotoList
        mSelectedPh0toAdapter.setNewData(mSelectedPhotoList)
        binding.rvSelectedPhotos.invalidate()



    }

    override fun createMomentComplete() {
        super.onBackPressed()

    }


    override fun showError(message: String) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    // Use the captured imageBitmap as needed

                }
                REQUEST_IMAGE_PICK -> {
                    val imageUri = data?.data

                    val inputStream = imageUri?.let { contentResolver.openInputStream(it) }
                    val uri = BitmapFactory.decodeStream(inputStream)

                    mPresenter.uploadImageFromGallery(uri)
                }
            }
        }
    }


}