package com.example.wechat_padc.delegates

import com.example.wechat_padc.data.VO.ContactsVO

interface SelectedContactsDelegate
{
    fun onTapContacts(userUID: ContactsVO)
}