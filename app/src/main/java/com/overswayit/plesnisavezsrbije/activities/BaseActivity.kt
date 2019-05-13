package com.overswayit.plesnisavezsrbije.activities

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var progressHUD: KProgressHUD? = null

    fun showProgress() {
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(this)
                    .setCancellable(false)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f)
        }
        progressHUD!!.show()
    }

    fun hideProgress() {
        if (progressHUD != null) {
            progressHUD!!.dismiss()
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
