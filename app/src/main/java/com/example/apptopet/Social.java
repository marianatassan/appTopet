package com.example.apptopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Social extends AppCompatActivity {

    SocialAdapter socialAdapter;
    List<MyItem> photos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        SocialViewModel vm = new ViewModelProvider(this).get(SocialViewModel.class);
        List<MyItem> photos = vm.getItems();

        socialAdapter = new SocialAdapter(this, photos);

        RecyclerView rvFotosSocial = findViewById(R.id.rvFotosSocial);
        rvFotosSocial.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFotosSocial.setLayoutManager(layoutManager);

        rvFotosSocial.setAdapter(socialAdapter);

        float w = getResources().getDimension(R.dimen.itemWidth);
        int numberOfColumns = Utils.calculateNumberOfColumns(Social.this, w);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Social.this, numberOfColumns);
        rvFotosSocial.setLayoutManager(gridLayoutManager);

    }
}