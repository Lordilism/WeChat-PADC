package com.example.wechat_padc.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.adapters.CheckableContactAdapter
import com.example.wechat_padc.adapters.SelectedContactAdapter
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.ActivityNewGroupBinding
import com.example.wechat_padc.mvp.presenters.NewGroupPresenterImpl
import com.example.wechat_padc.mvp.view.NewGroupView
import com.squareup.picasso.Picasso

class NewGroupActivity : BaseActivity(), NewGroupView {
    private lateinit var binding: ActivityNewGroupBinding

    private lateinit var mCheckableAdapter: CheckableContactAdapter
    private lateinit var mSelectedContactsAdapter: SelectedContactAdapter
    private lateinit var mLogoUrl: String


    private lateinit var mPresenter: NewGroupPresenterImpl

    private var mCurrentUserId = ""

    private var mSelectedContactsList = mutableListOf<ContactsVO>()
    private var members = mutableListOf<String>()

    private var mContact: List<ContactsVO> = listOf()

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 111
        const val REQUEST_IMAGE_PICK = 112
        fun newIntent(context: Context): Intent {
            return Intent(context, NewGroupActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewGroupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpPresenter()
        setUpListenters()

        setUpAdapters()
        mPresenter.onUiReady(this)
    }

    private fun setUpListenters() {
        binding.btnCreate.setOnClickListener {

            val groupName = binding.etGroupName.text.toString()
            mPresenter.onTapCreate(
                mLogoUrl,
                groupName,
                mSelectedContactsList,
                mCurrentUserId,
                System.currentTimeMillis()
            )
        }

        binding.btnClose.setOnClickListener {
            mPresenter.onTapClose()
        }
        binding.ivClear.setOnClickListener {
            deleteAllTextFromField()
        }

        binding.ivInsertLogo.setOnClickListener {
            mPresenter.onTapLogoImage()
        }


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(NewGroupPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapters() {
        mCheckableAdapter = CheckableContactAdapter(mPresenter)
        binding.rvCheckableContactsNewGroup.adapter = mCheckableAdapter
        binding.rvCheckableContactsNewGroup.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mSelectedContactsAdapter = SelectedContactAdapter()
        binding.rvSelectedContactsNewGroup.adapter = mSelectedContactsAdapter
        binding.rvSelectedContactsNewGroup.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun cancelGroupCreation() {
        super.onBackPressed()
    }

    override fun createGroup() {
        super.onBackPressed()


    }

    override fun deleteAllTextFromField() {
        binding.tvSearch.text.clear()

    }

    override fun showContactsList(listContactsVO: List<ContactsVO>) {
        mCheckableAdapter.setNewData(listContactsVO)
    }

    override fun showSelectedContactList(selectedContact: ContactsVO) {

        if (selectedContact.isSelected == true){
            mSelectedContactsList.add(selectedContact)
            mSelectedContactsAdapter.setNewData(mSelectedContactsList)
        }else{
            mSelectedContactsList.remove(selectedContact)
             mSelectedContactsAdapter.setNewData(mSelectedContactsList)
        }



//        mSelectedContactsList.add(selectedContact)
////        Log.d("contacts", mSelectedContactsList.toString())
//        mSelectedContactsAdapter.setNewData(mSelectedContactsList)


    }

    override fun showUserData(userUID: String) {
        mCurrentUserId = userUID
    }

    override fun openGallery() {
        val takeFromGallery =
            Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(takeFromGallery, REQUEST_IMAGE_PICK)
    }

    override fun showGroupLogo(logoUrl: String) {
        mLogoUrl = logoUrl
        Picasso.get()
            .load(logoUrl)
            .resize(100, 100)
            .into(binding.ivInsertLogo)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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