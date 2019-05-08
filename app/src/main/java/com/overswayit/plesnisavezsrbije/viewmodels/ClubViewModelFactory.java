package com.overswayit.plesnisavezsrbije.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by lazarristic on 30/04/2019.
 * Copyright (c) 2019 PlesniSavezSrbije. All rights reserved.
 */
public class ClubViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    private Integer id;

    public ClubViewModelFactory(Application application, int id) {
        this.application = application;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ClubViewModel(id, application);
    }
}
