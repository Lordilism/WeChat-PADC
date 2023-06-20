package com.example.wechat_padc.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.activities.ChatActivity
import com.example.wechat_padc.activities.NewGroupActivity
import com.example.wechat_padc.activities.ScannerQrActivity
import com.example.wechat_padc.adapters.ContactsAdapter
import com.example.wechat_padc.adapters.GroupAdapter
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.data.VO.GroupVO
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
//    private lateinit var mAlphabetList: List<String>
    private lateinit var mContactsList: List<String>


    private var _binding: FragmentContactsBinding? = null

    private val binding get() = _binding!!

    private lateinit var mPresenter: ContactsPresenterImpl

    private var mCurrentUserUID:String=""


    val fragmentLauncher: ActivityResultLauncher<ScanOptions> =
        registerForActivityResult(ScanContract()) { result ->
            if (result.contents == null) {
                Toast.makeText(requireContext(), "cancel", Toast.LENGTH_SHORT).show()
            } else {
                mPresenter.addNewContactOnStore(result.contents)
            }
        }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        activity?.requestedOrientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT    (chat-GPT)

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
//        mAlphabetList = mContactsList.groupBy { it.firstOrNull()?.uppercase() }.map { it.key ?: "" }
        setUpPresenter()
        setUpAdapters()
        setUpListeners()

        mPresenter.onUiReady(this)


    }

    private fun setUpListeners() {
        binding.btnAddNewGroup.setOnClickListener { createGroup() }

        binding.btnAddNewContact.setOnClickListener { mPresenter.onTapNewContact() }

        binding.ivClose.setOnClickListener { deleteAllTextFromField() }
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(ContactsPresenterImpl::class.java)
        mPresenter.intiView(this)
    }

    private fun setUpAdapters() {
        mGroupAdapter = GroupAdapter(mPresenter)
        binding.rvGroups.adapter = mGroupAdapter
        binding.rvGroups.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        mContactsAdapter = ContactsAdapter(mPresenter)
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

    override fun openQrScanner() {


        val options = ScanOptions().apply {
            captureActivity = ScannerQrActivity::class.java
            setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            setOrientationLocked(false)
            setBarcodeImageEnabled(true)
        }
        fragmentLauncher.launch(options)

    }

    override fun showSuccessMessage() {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
    }

    override fun showContactsList(contactsList: List<ContactsVO>) {
        mContactsAdapter.setNewData(contactsList)

    }

    override fun navigateToChatActivity(s: String) {
        startActivity(ChatActivity.newIntent(requireContext(),s,mCurrentUserUID,true))
        Toast.makeText(requireContext(),s,Toast.LENGTH_SHORT).show()

    }

    override fun currentUserUID(userUid: String) {
        mCurrentUserUID = userUid
    }

    override fun showGroup(listVO: MutableList<GroupVO>) {
        mGroupAdapter.setNewData(listVO)
    }

    override fun navigateToChatActivityFromGroup(groupID: String) {
        startActivity(ChatActivity.newIntent(requireContext(),groupID,mCurrentUserUID,false))
        Toast.makeText(requireContext(),groupID,Toast.LENGTH_SHORT).show()
    }


    override fun showError(message: String) {
        Toast.makeText(requireContext(), "Failed:due to $message", Toast.LENGTH_SHORT).show()
    }
}