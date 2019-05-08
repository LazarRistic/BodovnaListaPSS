package com.overswayit.plesnisavezsrbije.utils;

import android.text.TextUtils;

import com.overswayit.plesnisavezsrbije.R;
import com.overswayit.plesnisavezsrbije.models.AddressClubContact;
import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.models.ClubContact;
import com.overswayit.plesnisavezsrbije.models.EmailClubContact;
import com.overswayit.plesnisavezsrbije.models.PhoneClubContact;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by lazarristic on 18/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubUtil {

    public static String getClubNameAndTown(Club club) {
        if (club != null) {
            return getClubNameAndTown(club.name, club.town);
        }

        return StringUtil.getString(R.string.not_available);
    }

    public static String getClubNameAndTown(String name, String town) {
        StringBuilder stringBuilder = new StringBuilder();

        if (TextUtils.isEmpty(name)) {
            name = StringUtil.getString(R.string.not_available);
        }

        if (TextUtils.isEmpty(town)) {
            town = StringUtil.getString(R.string.not_available);
        }

        return stringBuilder.append(name).append(" - ").append(town).toString();
    }

    public static ArrayList<ClubContact> getClubContacts(@NotNull Club club) {
        ArrayList<ClubContact> clubContacts = new ArrayList<>();

        clubContacts.add(new AddressClubContact(club.address));
        clubContacts.add(new EmailClubContact(club.email));

        for (String phone : club.phoneNumbers) {
            clubContacts.add(new PhoneClubContact(phone));
        }

        return clubContacts;
    }
}
