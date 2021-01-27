package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import java.util.ArrayList;
import java.util.List;

public class Social extends AppCompatActivity {

    SocialAdapter socialAdapter;
    List<MyItem> photos = new ArrayList<>();
    static int PHOTO_PICKER_REQUEST = 1;
    Uri selectedPhotoLocation;
    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        SocialViewModel vm = new ViewModelProvider(this).get(SocialViewModel.class);

        FloatingActionButton fabAddNewItem = findViewById(R.id.floatingActionButton); // Pega o id do botão de adicionar;
        fabAddNewItem.setOnClickListener(new View.OnClickListener() { // Determina que, ao clicar no botão, uma função será executada;
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Social.this, NovaPostagem.class); // estabelece uma intenção de navegação entre telas;
                startActivityForResult(i, NEW_ITEM_REQUEST); //executa uma intenção que envolve a adição de itens e que retorna um resultado;
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

}