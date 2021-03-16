package com.example.apptopet.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class ListaCompromissosViewModel extends AndroidViewModel {
    LiveData<List<Compromisso>> compromissos;

    public ListaCompromissosViewModel(@NonNull Application application, long data) {
        super(application);
        compromissos = MyDB.getInstance(application).myDAO().getCompromissos(data);
    }

    public LiveData<List<Compromisso>> getItems() {
        return compromissos;
    }

    static public class ListaCompromissosViewModelFactory implements ViewModelProvider.Factory {
        Application application;
        long data;

        public ListaCompromissosViewModelFactory(Application application, long data) {
            this.application = application;
            this.data = data;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ListaCompromissosViewModel(application, data);
        }
    }
}
