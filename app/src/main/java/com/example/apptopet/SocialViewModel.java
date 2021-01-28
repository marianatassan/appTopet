package com.example.apptopet;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SocialViewModel extends ViewModel {
    List<MyItem> photos = new ArrayList<>();

    public SocialViewModel () {
        MyItem postagem1 = new MyItem();
        postagem1.postagem = R.drawable.calopsita;
        MyItem postagem2 = new MyItem();
        postagem2.postagem= R.drawable.coelho;
        MyItem postagem3 = new MyItem();
        postagem3.postagem = R.drawable.gatoo;

        photos.add(postagem1);
        photos.add(postagem2);
        photos.add(postagem3);
    }

    public List<MyItem> getItems() {
        return photos;
    }
}
