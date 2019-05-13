package com.overswayit.plesnisavezsrbije.models;

import android.util.Pair;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.utils.PhoneUtil;

import java.util.ArrayList;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class PhoneClubContact extends ClubContact {

    public PhoneClubContact(String phone) {
        super(phone);

        if (PhoneUtil.isMobilePhone(phone)) {
            createMobilePhoneClubContact();
            return;
        }

        createLandLinePhoneClubContact();
    }

    private void createMobilePhoneClubContact() {
        Pair<String, Integer> call = new Pair<>(super.getContact(), R.drawable.ic_local_phone);
        Pair<String, Integer> message = new Pair<>(super.getContact(), R.drawable.ic_message);
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        arrayList.add(call);
        arrayList.add(message);

        super.setContacts(arrayList);
        super.setType(ClubContactType.MOBILE);
    }

    private void createLandLinePhoneClubContact() {
        Pair<String, Integer> call = new Pair<>(super.getContact(), R.drawable.ic_local_phone);
        ArrayList<Pair<String, Integer>> arrayList = new ArrayList<>();
        arrayList.add(call);

        super.setContacts(arrayList);
        super.setType(ClubContactType.LAND_LINE);
    }
}
