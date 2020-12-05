package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SocialViewModel extends ViewModel {
    List<MyItem> photos = new ArrayList<>();

    public SocialViewModel() {
        MyItem foto1 = new MyItem();
        foto1.foto_social = R.drawable.cachorro;
        MyItem foto2 = new MyItem();
        foto2.foto_social = R.drawable.gato;
        MyItem foto3 = new MyItem();
        foto3.foto_social = R.drawable.calopsita;
        MyItem foto4 = new MyItem();
        foto4.foto_social = R.drawable.coelho;
        MyItem foto5 = new MyItem();
        foto5.foto_social = R.drawable.hamster;
        MyItem foto6 = new MyItem();
        foto6.foto_social = R.drawable.gatoo;

        photos.add(foto1);
        photos.add(foto2);
        photos.add(foto3);
        photos.add(foto4);
        photos.add(foto5);
        photos.add(foto6);
    }
    public List<MyItem> getItems() {
        return photos;
    }
}
