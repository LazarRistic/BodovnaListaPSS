package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import com.overswayit.plesnisavezsrbije.models.Club;
import com.overswayit.plesnisavezsrbije.repository.ClubsRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by lazarristic on 19/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubsViewModel extends AndroidViewModel {
    private ClubsRepository clubsRepository;

    public ClubsViewModel(@NonNull Application application) {
        super(application);

        clubsRepository = new ClubsRepository(application);
    }

    public LiveData<List<Club>> getAllClubs() {
        return clubsRepository.getClubsLiveData();
    }
}
