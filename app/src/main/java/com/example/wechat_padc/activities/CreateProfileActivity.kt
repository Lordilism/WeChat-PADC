package com.example.wechat_padc.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.wechat_padc.databinding.ActivityCreateProfileBinding
import com.example.wechat_padc.mvp.presenters.CreatProfilePresenterImpl
import com.example.wechat_padc.mvp.view.CreatProfileView
import com.example.wechat_padc.utils.day
import com.example.wechat_padc.utils.yearList
import com.squareup.picasso.Picasso

class CreateProfileActivity : BaseActivity(), CreatProfileView {
    private lateinit var binding: ActivityCreateProfileBinding

    private lateinit var mPresenter: CreatProfilePresenterImpl

    private var mGender: String = "other"
    private var mUserProfile: String = ""

    companion object {
        const val GALLERY_REQUEST_CODE = 101
        fun newIntent(context: Context): Intent {
            return Intent(context, CreateProfileActivity::class.java)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            val inputStream = contentResolver.openInputStream(imageUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            // Process the selected image URI here
            mPresenter.uploadImage(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpPresenter()
        setUpRadioButtons()
        setUpSpinners()
        setUpListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, day)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerDay.adapter = adapter
        binding.spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val day = parent?.getItemAtPosition(position).toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val adapterYear = ArrayAdapter(this, android.R.layout.simple_spinner_item, yearList)
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerYear.adapter = adapterYear

        binding.spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    val year = parent?.getItemAtPosition(position).toString()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    private fun setUpRadioButtons() {
        binding.rbOther.isChecked = true

        binding.rbFemale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Female"
                binding.rbOther.isChecked = false
                binding.rbMale.isChecked = false
            }
        }
        binding.rbMale.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Male"
                binding.rbOther.isChecked = false
                binding.rbFemale.isChecked = false
            }
        }

        binding.rbOther.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mGender = "Other"
                binding.rbFemale.isChecked = false
                binding.rbMale.isChecked = false
            }
        }


    }

    private fun setUpListeners() {

//        if (binding.cbAgreeToTermAndService.isChecked) {
//            if (
//                binding.etNameSignUp.text.toString().trim().isNotEmpty() &&
//                binding.edtEmailSignUp.text.toString().trim().isNotEmpty() &&
//                binding.etPasswordSignUp.text.toString().trim().isNotEmpty() &&
//                binding.spinnerDay.selectedItem.toString() != "Day" &&
//                binding.spinnerMonth.selectedItem.toString() != "Month" &&
//                binding.spinnerYear.selectedItem.toString() != "Year"
//            ) {
//                binding.btnSignUpFinish.background =
//                    ContextCompat.getDrawable(this, R.drawable.background_button_log_in)
//
//                binding.btnSignUpFinish.setOnClickListener {
//
//                }
//            } else {
//                binding.btnSignUpFinish.background =
//                    ContextCompat.getDrawable(this, R.drawable.bkg_button_nclick)
//            }
//        } else {
//            binding.btnSignUpFinish.background =
//                ContextCompat.getDrawable(this, R.drawable.bkg_button_nclick)
//
//        }
        binding.btnSignUpFinish.setOnClickListener {
            mPresenter.onTapSignUp(
                email = binding.edtEmailSignUp.text.toString().trim(),
                name = binding.etNameSignUp.text.toString().trim(),
                password = binding.etPasswordSignUp.text.toString().trim(),
                day = binding.spinnerDay.selectedItem.toString(),
                month = binding.spinnerMonth.selectedItem.toString(),
                year = binding.spinnerYear.selectedItem.toString(),
                gender = mGender,
                userProfile = mUserProfile

            )
        }

        binding.tvUploadProfile.setOnClickListener {
            mPresenter.onTapProfileUpload()
        }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreatProfilePresenterImpl::class.java]
        mPresenter.initView(this)

    }

    override fun navigateToLogIn() {
        startActivity(LogInActivity.newIntent(this))
    }

    override fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun bindImage(url: String) {
        mUserProfile = url
        Picasso.get()
            .load(url)
            .resize(200,200)
            .into(binding.ivUserProfile)

    }

    override fun showError(message: String) {

    }
}