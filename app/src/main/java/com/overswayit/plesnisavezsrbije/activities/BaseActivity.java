package com.overswayit.plesnisavezsrbije.activities;

import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private KProgressHUD progressHUD;

    public void showProgress() {
        if (progressHUD == null) {
            progressHUD = KProgressHUD.create(this)
                    .setCancellable(false)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f);
        }
        progressHUD.show();
    }

    public void hideProgress() {
        if (progressHUD != null) {
            progressHUD.dismiss();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
