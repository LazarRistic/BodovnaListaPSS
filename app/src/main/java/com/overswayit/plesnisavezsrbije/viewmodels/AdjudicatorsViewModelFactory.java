package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.overswayit.plesnisavezsrbije.models.AdjudicatorLicensesType;

/**
 * Created by lazarristic on 2019-05-09.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class AdjudicatorsViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private AdjudicatorLicensesType licensesType;

    public AdjudicatorsViewModelFactory(Application application, AdjudicatorLicensesType licensesType) {
        this.application = application;
        this.licensesType = licensesType;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AdjudicatorsViewModel(application, licensesType);
    }
}
