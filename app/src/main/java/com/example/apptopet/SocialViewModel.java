package com.example.apptopet;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SocialViewModel extends ViewModel {

    List<MyItem> photos = new ArrayList<>();

    public SocialViewModel () {
        MyItem postagem1 = new MyItem();
        postagem1.postagem = R.drawable.calopsita;
        postagem1.tituloFix = "Kevin";
        MyItem postagem2 = new MyItem();
        postagem2.postagem= R.drawable.coelho;
        postagem2.tituloFix = "Luna";
        MyItem postagem3 = new MyItem();
        postagem3.postagem = R.drawable.gatoo;
        postagem3.tituloFix = "Dominique";

        photos.add(postagem1);
        photos.add(postagem2);
        photos.add(postagem3);
    }

    public List<MyItem> getItems() {
        return photos;
    }
}
