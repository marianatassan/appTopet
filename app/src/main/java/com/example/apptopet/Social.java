package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Social extends AppCompatActivity {

    SocialAdapter socialAdapter;
    List<MyItem> photos = new ArrayList<>();
    static int PHOTO_PICKER_REQUEST = 1;
    Uri selectPhotoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        SocialViewModel vm = new ViewModelProvider(this).get(SocialViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        if(selectPhotoLocation != null) {
            ImageView imvProfile = findViewById(R.id.imvProfile);
            imvProfile.setImageURI(selectPhotoLocation);
        }

        FloatingActionButton btnAddFoto = findViewById(R.id.floatingActionButton);
        btnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);

            }
        });

        SocialViewModel vm2 = new ViewModelProvider(this).get(SocialViewModel.class);
        List<MyItem> photos = vm2.getItems();

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
                selectPhotoLocation = data.getData();
                ImageView imvPerfil = findViewById(R.id.imvSocial);
                imvPerfil.setImageURI(selectPhotoLocation);

                SocialViewModel vm = new ViewModelProvider(this).get(SocialViewModel.class);
                vm.setSelectPhotoLocation(selectPhotoLocation); // Armazena o Uri da foto dentro do ViewModel;

                MyItem myItem = new MyItem();
                myItem.fotoSocial = selectPhotoLocation;

                SocialViewModel vm3 = new ViewModelProvider(this).get(SocialViewModel.class);
                List<MyItem> photos = vm3.getItems();

                photos.add(myItem);
            }
        }
    }
}