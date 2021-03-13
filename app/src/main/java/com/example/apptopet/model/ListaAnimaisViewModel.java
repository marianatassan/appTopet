package com.example.apptopet.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaAnimaisViewModel extends AndroidViewModel {

    List<Animal> animais = new ArrayList<>();

    public ListaAnimaisViewModel(final Application application) {
        super(application);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                animais = MyDB.getInstance(application).myDAO().getAnimais();
            }
        });
    }

    public List<Animal> getItems() {
        return animais;
    }
}
