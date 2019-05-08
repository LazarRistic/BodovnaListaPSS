package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.repository.ClubRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by lazarristic on 23/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubViewModel extends AndroidViewModel {
    private ClubRepository clubRepository;

    public ClubViewModel(int id, @NonNull Application application) {
        super(application);
        this.clubRepository = new ClubRepository(id, application);
    }

    public LiveData<Club> getClub() {
        return clubRepository.getClubLiveData();
    }
}
