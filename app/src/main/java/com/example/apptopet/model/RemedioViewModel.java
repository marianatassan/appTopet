package com.example.apptopet.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class RemedioViewModel extends AndroidViewModel {
    LiveData<List<Medication>> medications;

    public RemedioViewModel(@NonNull Application application, String nomeAnimal) {
        super(application);
        medications = MyDB.getInstance(application).myDAO().getMedicationsAnimal(nomeAnimal);
    }

    public LiveData<List<Medication>> getItems() {
        return medications;
    }

    static public class RemedioViewModelFactory implements ViewModelProvider.Factory {
        Application application;
        String nomeAnimal;

        public RemedioViewModelFactory(Application application, String nomeAnimal) {
            this.application = application;
            this.nomeAnimal = nomeAnimal;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RemedioViewModel(application, nomeAnimal);
        }
    }
}
