package com.overswayit.plesnisavezsrbije

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

import com.overswayit.plesnisavezsrbije.activities.BaseActivity

import java.lang.ref.WeakReference

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class App : Application() {

    private val topActivityService = TopActivityService()

    override fun onCreate() {
        super.onCreate()

        registerActivityLifecycleCallbacks(topActivityService)
        CONTEXT = this
    }

    class TopActivityService : ActivityLifecycleCallbacks {

        private var baseActivityWeakReference: WeakReference<BaseActivity>? = null

        val topBaseActivity: BaseActivity?
            get() = if (baseActivityWeakReference != null) {
                baseActivityWeakReference!!.get()
            } else null

        override fun onActivityCreated(activity: Activity, bundle: Bundle) {

        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityResumed(activity: Activity) {
            if (activity is BaseActivity) {
                if (baseActivityWeakReference != null) {
                    baseActivityWeakReference!!.clear()
                }

                baseActivityWeakReference = WeakReference(activity)
            }
        }

        override fun onActivityPaused(activity: Activity) {
            if (baseActivityWeakReference != null && baseActivityWeakReference!!.get() === activity) {
                baseActivityWeakReference!!.clear()
                baseActivityWeakReference = null
            }

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {

        }
    }

    companion object {

        private var CONTEXT: Context? = null

        val context: Context
            get() {

                if (CONTEXT == null) {
                    throw RuntimeException("Calling App.getContext() before App instance's onCreate()")
                }

                return CONTEXT as Context
            }

        fun hideSoftKeyboard(activity: Activity) {
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            hideSoftKeyboard(activity.findViewById<View>(android.R.id.content))
        }

        fun hideSoftKeyboard(view: View) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        val topBaseActivity: BaseActivity?
            get() = (context as App).topActivityService.topBaseActivity
    }
}
