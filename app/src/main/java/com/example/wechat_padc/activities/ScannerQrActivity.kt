package com.example.wechat_padc.activities

import android.content.Context
import android.content.Intent
import com.journeyapps.barcodescanner.CaptureActivity


class ScannerQrActivity : CaptureActivity() {
    //    private lateinit var mCaptureManager: CaptureManager
//    private lateinit var mDecoratedBarcodeView: DecoratedBarcodeView


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ScannerQrActivity::class.java)
        }
    }


}





