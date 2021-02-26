package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

import java.util.ArrayList;
import java.util.List;

public class PostsViewModel extends ViewModel {

    List<MyItem> photos = new ArrayList<>();

    public PostsViewModel() {
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
