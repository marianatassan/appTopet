package com.example.apptopet.model;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.apptopet.activity.AdicionarCompromissoActivity;
import com.example.apptopet.activity.PerfilAnimalActivity;

import java.util.ArrayList;
import java.util.List;

public class PerfilAnimalViewModel extends AndroidViewModel {

    LiveData<List<Compromisso>> compromissos;

    public PerfilAnimalViewModel(@NonNull Application application, String nomeAnimal, long data) {
        super(application);
        compromissos = MyDB.getInstance(application).myDAO().getCompromissosAnimal(nomeAnimal, data);
    }

    public LiveData<List<Compromisso>> getItems() {
        return compromissos;
    }

    static public class PerfilAnimalViewModelFactory implements ViewModelProvider.Factory {
        Application application;
        String nomeAnimal;
        long data;

        public PerfilAnimalViewModelFactory(Application application, String nomeAnimal, long data) {
            this.application = application;
            this.nomeAnimal = nomeAnimal;
            this.data = data;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new PerfilAnimalViewModel(application, nomeAnimal, data);
        }
    }
}
