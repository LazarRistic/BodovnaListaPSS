package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import com.overswayit.plesnisavezsrbije.models.Adjudicator;
import com.overswayit.plesnisavezsrbije.repository.AdjudicatorsReposetory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by lazarristic on 21/02/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorsViewModel extends AndroidViewModel {

    private AdjudicatorsReposetory adjudicatorsReposetory;

    public AdjudicatorsViewModel(@NonNull Application application) {
        super(application);

        adjudicatorsReposetory = new AdjudicatorsReposetory(application);
    }

    public LiveData<List<Adjudicator>> getAllAdjudicators() {
        return adjudicatorsReposetory.getAdjudicatorsLiveData();
    }
}
