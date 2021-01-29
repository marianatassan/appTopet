package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Social extends AppCompatActivity {

    SocialAdapter socialAdapter;
    static int PHOTO_PICKER_REQUEST = 1;
    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        FloatingActionButton fabAddNewItem = findViewById(R.id.floatingActionButton);
        fabAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Social.this, NovaPostagem.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedPhotoLocation = data.getData();
                String title = data.getStringExtra("title");

                MyItem newItem = new MyItem();
                newItem.fotoSocial = selectedPhotoLocation;
                newItem.titulo = title;

                SocialViewModel vm = new ViewModelProvider(this).get(SocialViewModel.class);
                List<MyItem> photos = vm.getItems();

                photos.add(newItem);

                socialAdapter.notifyItemInserted(photos.size()-1);
            }
        }
    }

    public void startZoomSocial(MyItem photos) {
        Intent i = new Intent(Social.this, ZoomSocial.class);
        startActivity(i);
    }

}