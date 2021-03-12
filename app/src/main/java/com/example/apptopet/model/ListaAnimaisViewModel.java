package com.example.apptopet.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;

public class ListaAnimaisViewModel extends AndroidViewModel {
    List<Animal> animais = new ArrayList<>();

    public ListaAnimaisViewModel(Application application) {
        super(application);
        animais = MyDB.getInstance(application).myDAO().getAnimais();
    }

    public List<Animal> getItems() {
        return animais;
    }
}
