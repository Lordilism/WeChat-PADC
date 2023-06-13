package com.example.wechat_padc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechat_padc.R
import com.example.wechat_padc.adapters.ActiveUserAdapter
import com.example.wechat_padc.adapters.ChatListAdapter
import com.example.wechat_padc.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    private lateinit var mActiveUserAdapter: ActiveUserAdapter
    private lateinit var mChatListAdapter: ChatListAdapter
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
        _binding = FragmentChatBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpActiveUserRecyclerView()
        setUpChatListRecyclerView()


    }

    private fun setUpChatListRecyclerView() {
        mChatListAdapter = ChatListAdapter()
        binding.rvChats.adapter = mChatListAdapter
        binding.rvChats.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

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




}