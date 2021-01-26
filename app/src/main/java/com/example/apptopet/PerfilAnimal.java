package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PerfilAnimal extends AppCompatActivity {
    MyAdapter2 myAdapter2;
    static int PHOTO_PICKER_REQUEST = 1;
    Uri selectPhotoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        PerfilAnimalViewModel vm = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        if(selectPhotoLocation != null) {
            ImageView imvProfile = findViewById(R.id.imvProfile);
            imvProfile.setImageURI(selectPhotoLocation);
        }

        ImageView imvProfile = findViewById(R.id.imvProfile);
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);

            }
        });

        Button btnSaude = findViewById(R.id.btnSaude);
        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilAnimal.this, Saude.class);
                startActivity(i);
            }
        });
        PerfilAnimalViewModel vm2 = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);
        List<MyItem> compromissos = vm2.getItems();

        myAdapter2 = new MyAdapter2(this, compromissos);

        RecyclerView  rvCompromissos = findViewById(R.id.rvCompromissos);
        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        rvCompromissos.setAdapter(myAdapter2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                selectPhotoLocation = data.getData();
                ImageView imvPerfil = findViewById(R.id.imvProfile);
                imvPerfil.setImageURI(selectPhotoLocation);

                PerfilAnimalViewModel vm = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);
                vm.setSelectPhotoLocation(selectPhotoLocation); // Armazena o Uri da foto dentro do ViewModel;
            }
        }
    }
}