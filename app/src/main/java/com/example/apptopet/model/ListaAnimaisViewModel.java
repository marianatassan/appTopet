package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;

public class ListaAnimaisViewModel extends ViewModel {
    List<MyItem> animais = new ArrayList<>();

    public ListaAnimaisViewModel() {
        MyItem animal1 = new MyItem();
        animal1.nome = "Dudu";
        animal1.foto = R.drawable.cachorro;
        MyItem animal2 = new MyItem();
        animal2.nome = "Toby";
        animal2.foto = R.drawable.gato;
        MyItem animal3 = new MyItem();
        animal3.nome = "Mingau";
        animal3.foto = R.drawable.gatoo;
        MyItem animal4 = new MyItem();
        animal4.nome = "Tico";
        animal4.foto = R.drawable.calopsita;
        MyItem animal5 = new MyItem();
        animal5.nome = "Lulu";
        animal5.foto = R.drawable.hamster;
        MyItem animal6 = new MyItem();
        animal6.nome = "Thor";
        animal6.foto = R.drawable.coelho;

        animais.add(animal1);
        animais.add(animal2);
        animais.add(animal3);
        animais.add(animal4);
        animais.add(animal5);
        animais.add(animal6);
    }

    public List<MyItem> getItems() {
        return animais;
    }
}
