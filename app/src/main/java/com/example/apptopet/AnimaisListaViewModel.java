package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class AnimaisListaViewModel extends ViewModel {
    List<MyItem> animais = new ArrayList<>();

    public List<MyItem> getItems() {
        return animais;
    }
}
