package com.overswayit.plesnisavezsrbije;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.overswayit.plesnisavezsrbije.activities.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class App extends Application {

    private static Context CONTEXT;

    public static Context getContext() {

        if (CONTEXT == null) {
            throw new RuntimeException("Calling App.getContext() before App instance's onCreate()");
        }

        return CONTEXT;
    }

    private TopActivityService topActivityService = new TopActivityService();

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(topActivityService);
        CONTEXT = this;
    }

    public static void hideSoftKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        hideSoftKeyboard(activity.findViewById(android.R.id.content));
    }

    public static void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static BaseActivity getTopBaseActivity() {
        return ((App) getContext()).topActivityService.getTopBaseActivity();
    }

    public static class TopActivityService implements ActivityLifecycleCallbacks {

        WeakReference<BaseActivity> baseActivityWeakReference;

        public BaseActivity getTopBaseActivity() {
            if (baseActivityWeakReference != null) {
                return baseActivityWeakReference.get();
            }

            return null;
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (activity instanceof BaseActivity) {
                if (baseActivityWeakReference != null) {
                    baseActivityWeakReference.clear();
                }

                baseActivityWeakReference = new WeakReference<>((BaseActivity) activity);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (baseActivityWeakReference != null && baseActivityWeakReference.get() == activity) {
                baseActivityWeakReference.clear();
                baseActivityWeakReference = null;
            }

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    }
}
