package com.example.apptopet.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaAnimaisViewModel extends AndroidViewModel {

    LiveData<List<Animal>> animais;

    public ListaAnimaisViewModel(final Application application) {
        super(application);
        animais = MyDB.getInstance(application).myDAO().getAnimais();
    }

    public LiveData<List<Animal>> getItems() {
        return animais;
    }
}
