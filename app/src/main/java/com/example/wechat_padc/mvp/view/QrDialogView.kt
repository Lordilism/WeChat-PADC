package com.example.wechat_padc.mvp.view

import android.graphics.Bitmap

interface QrDialogView:BaseView {
    fun bindQr(bitmap: Bitmap)
}