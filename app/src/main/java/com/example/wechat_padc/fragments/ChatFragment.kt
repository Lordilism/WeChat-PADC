package com.example.wechat_padc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.activities.ChatActivity
import com.example.wechat_padc.adapters.ActiveUserAdapter
import com.example.wechat_padc.adapters.ChatListAdapter
import com.example.wechat_padc.adapters.GroupMessageAdapter
import com.example.wechat_padc.data.VO.ContactsVO
import com.example.wechat_padc.databinding.FragmentChatBinding
import com.example.wechat_padc.mvp.presenters.ChatFragmentPresenterImpl
import com.example.wechat_padc.mvp.view.ChatFragmentView


class ChatFragment : Fragment(), ChatFragmentView {
    private lateinit var mActiveUserAdapter: ActiveUserAdapter
    private lateinit var mChatListAdapter: ChatListAdapter
    private lateinit var mGroupAdater: GroupMessageAdapter

    private lateinit var mPresenter: ChatFragmentPresenterImpl
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()
        setUpActiveUserRecyclerView()
        setUpGroupRecyclerView()
        setUpChatListRecyclerView()
        mPresenter.onUiReady(this)


    }

    private fun setUpGroupRecyclerView() {
        mGroupAdater = GroupMessageAdapter(mPresenter)
        binding.rvGroupChat.adapter = mGroupAdater
        binding.rvGroupChat.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(ChatFragmentPresenterImpl::class.java)
        mPresenter.initView(this)

    }

    private fun setUpChatListRecyclerView() {
        mChatListAdapter = ChatListAdapter(mPresenter)
        binding.rvChats.adapter = mChatListAdapter
        binding.rvChats.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun setUpActiveUserRecyclerView() {
        mActiveUserAdapter = ActiveUserAdapter()
        binding.rvActiveUser.adapter = mActiveUserAdapter
        binding.rvActiveUser.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showActiveUser(listContactVO: List<ContactsVO>) {
        mActiveUserAdapter.setNewData(listContactVO)
    }

    override fun showLatestMessage(messageList: List<String>, signInUserUID: String) {
        mChatListAdapter.setNewData(messageList, signInUserUID)
    }

    override fun showJoindGroups(joinedGroup: MutableSet<Long>) {

        mGroupAdater.setNewData(joinedGroup)

    }

    override fun navigateToChat(msgReceiverUid: String, userUID: String) {
        startActivity(ChatActivity.newIntent(requireContext(), msgReceiverUid, userUID, true))
    }

    override fun navigateToChatGroup(groupID: String, mCurrentUserUID: String) {
        startActivity(ChatActivity.newIntent(requireContext(),groupID,mCurrentUserUID,false))
    }

    override fun showError(message: String) {

    }


}