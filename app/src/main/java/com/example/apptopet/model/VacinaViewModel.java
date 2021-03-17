package com.example.apptopet.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class VacinaViewModel extends AndroidViewModel {
    LiveData<List<Vaccine>> vaccines;

    public VacinaViewModel(@NonNull Application application, String nomeAnimal) {
        super(application);
        vaccines = MyDB.getInstance(application).myDAO().getVaccinesAnimal(nomeAnimal);
    }

    public LiveData<List<Vaccine>> getItems() {
        return vaccines;
    }

    static public class VacinaViewModelFactory implements ViewModelProvider.Factory {
        Application application;
        String nomeAnimal;

        public VacinaViewModelFactory(Application application, String nomeAnimal) {
            this.application = application;
            this.nomeAnimal = nomeAnimal;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new VacinaViewModel(application, nomeAnimal);
        }
    }
}
