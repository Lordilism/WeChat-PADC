package com.example.wechat_padc.fragments

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.activities.NewGroupActivity
import com.example.wechat_padc.activities.ScannerQrActivity
import com.example.wechat_padc.adapters.ContactsAdapter
import com.example.wechat_padc.adapters.GroupAdapter
import com.example.wechat_padc.databinding.FragmentContactsBinding
import com.example.wechat_padc.dummy.AlphabetClickListener
import com.example.wechat_padc.dummy.contactsSampleList
import com.example.wechat_padc.mvp.presenters.ContactsPresenterImpl
import com.example.wechat_padc.mvp.view.ContactsView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions


class ContactsFragment : Fragment(), AlphabetClickListener, ContactsView {
    private lateinit var mGroupAdapter: GroupAdapter
    private lateinit var mContactsAdapter: ContactsAdapter

    //    private lateinit var mAlphabetAdapter: AlphabetAdapter
    private lateinit var mAlphabetList: List<String>
    private lateinit var mContactsList: List<String>


    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    private lateinit var mPresenter: ContactsPresenterImpl


    val fragmentLauncher: ActivityResultLauncher<ScanOptions> =
        registerForActivityResult(ScanContract()) { result ->
            if (result.contents == null) {
                Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "${result.contents}", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        activity?.requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT    (chat-GPT)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {


        super.onConfigurationChanged(newConfig)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mContactsList = contactsSampleList.sorted()
        mAlphabetList = mContactsList.groupBy { it.firstOrNull()?.uppercase() }.map { it.key ?: "" }
        setUpPresenter()
        setUpAdapters()
        setUpListeners()

        mPresenter.onUiReady(this)


    }

    private fun setUpListeners() {
        binding.btnAddNewGroup.setOnClickListener { createGroup() }

        binding.btnAddNewContact.setOnClickListener { addNewContact() }

        binding.ivClose.setOnClickListener { deleteAllTextFromField() }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(ContactsPresenterImpl::class.java)
        mPresenter.intiView(this)
    }

    private fun setUpAdapters() {
        mGroupAdapter = GroupAdapter()
        binding.rvGroups.adapter = mGroupAdapter
        binding.rvGroups.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        mContactsAdapter = ContactsAdapter(mContactsList)
        binding.rvContacts.adapter = mContactsAdapter
        binding.rvContacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onAlphabetClick(alphabet: String) {
//        val position = mContactsList.indexOf(alphabet)


    }

    override fun searhContacts() {

    }

    override fun createGroup() {
        startActivity(NewGroupActivity.newIntent(requireContext()))

    }

    override fun deleteAllTextFromField() {


    }

    override fun addNewContact() {
        val options = ScanOptions().apply {
            captureActivity = ScannerQrActivity::class.java
            setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            setOrientationLocked(false)
            setBarcodeImageEnabled(true)
        }
        fragmentLauncher.launch(options)

    }

    override fun showError(message: String) {

    }
}