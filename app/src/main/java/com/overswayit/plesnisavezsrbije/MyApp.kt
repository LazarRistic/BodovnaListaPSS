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
 * Created by lazarristic on 2019-05-21.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
class MyApp : Application() {

    private val topActivityService: TopActivityService = TopActivityService()

    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        fun applicationContext(): MyApp {
            return instance as MyApp
        }

    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(topActivityService)
    }

    fun hideSoftKeyboard(activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        hideSoftKeyboard(activity.findViewById<View>(android.R.id.content))
    }

    fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getTopBaseActivity(): BaseActivity? {
        return (applicationContext as MyApp).topActivityService.getTopBaseActivity()
    }

    class TopActivityService : ActivityLifecycleCallbacks {

        var baseActivityWeakReference: WeakReference<BaseActivity>? = null

        fun getTopBaseActivity(): BaseActivity? {
            return baseActivityWeakReference?.get()
        }

        override fun onActivityPaused(activity: Activity?) {
            if (baseActivityWeakReference?.get() == activity) {
                baseActivityWeakReference?.clear()
                baseActivityWeakReference = null
            }
        }

        override fun onActivityResumed(activity: Activity?) {
            if (activity is BaseActivity) {
                baseActivityWeakReference?.clear()
                baseActivityWeakReference = WeakReference(activity)
            }
        }

        override fun onActivityStarted(activity: Activity?) {

        }

        override fun onActivityDestroyed(activity: Activity?) {

        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

        }

        override fun onActivityStopped(activity: Activity?) {

        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

        }

    }
}