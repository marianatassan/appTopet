package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;

public class ListaAnimaisViewModel extends ViewModel {
    List<MyItem> animais = new ArrayList<>();

    public List<MyItem> getItems() {
        return animais;
    }
}
