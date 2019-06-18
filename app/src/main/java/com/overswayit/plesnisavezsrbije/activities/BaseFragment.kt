package com.overswayit.plesnisavezsrbije.activities

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * Created by lazarristic on 2019-06-17.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
abstract class BaseFragment : Fragment() {

    private var progressHUD: KProgressHUD? = null

    fun showProgress() {
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(activity)
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
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}