package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptopet.R;
import com.example.apptopet.adapter.PerfilAnimalAdapter;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.PerfilAnimalViewModel;
import com.example.apptopet.util.Utils;

import java.util.List;

public class PerfilAnimalActivity extends AppCompatActivity {

    PerfilAnimalAdapter perfilAnimalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        Intent i = getIntent();
        String fotoPerfilAnimal = i.getStringExtra("fotoPerfilAnimal");
        String nomeAnimal = i.getStringExtra("nomeAnimal");
        String raca = i.getStringExtra("raca");
        String dt_nasc = i.getStringExtra("dt_nasc");

        //PerfilAnimalViewModel vm = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);

        //Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        Bitmap bmp = Utils.getBitmap(fotoPerfilAnimal);
        ImageView imvProfile = findViewById(R.id.imvProfile);
        imvProfile.setImageBitmap(bmp);

        TextView tvNomeAnimal = findViewById(R.id.tvNomeAnimal);
        tvNomeAnimal.setText(nomeAnimal);

        TextView tvRaca = findViewById(R.id.tvRaca);
        tvRaca.setText(raca);

        TextView tvDtNasc = findViewById(R.id.tvDtNascimento);
        tvDtNasc.setText(dt_nasc);


        Button btnSaude = findViewById(R.id.btnSaude);
        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilAnimalActivity.this, SaudeActivity.class);
                startActivity(i);
            }
        });

        PerfilAnimalViewModel vm2 = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);
        List<Compromisso> compromissos = vm2.getCompromissos();

        perfilAnimalAdapter = new PerfilAnimalAdapter(this, compromissos);

        RecyclerView  rvCompromissos = findViewById(R.id.rvCompromissos);
        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        rvCompromissos.setAdapter(perfilAnimalAdapter);
    }
}