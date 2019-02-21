package com.overswayit.plesnisavezsrbije.viewmodels;

import android.text.TextUtils;
import android.view.View;

import com.overswayit.plesnisavezsrbije.utils.PhoneUtil;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubContactPhoneViewModel {
    private String phoneNumber;

    public ClubContactPhoneViewModel(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int showCallButton() {
        return !TextUtils.isEmpty(phoneNumber) ? View.VISIBLE : View.GONE;
    }

    public int showMessageButton() {
        return PhoneUtil.isMobilePhone(phoneNumber) ? View.VISIBLE : View.GONE;
    }
}
