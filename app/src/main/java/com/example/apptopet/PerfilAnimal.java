package com.example.apptopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class PerfilAnimal extends AppCompatActivity {

    MyAdapter2 myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        Button btnSaude = findViewById(R.id.btnSaude);
        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilAnimal.this, Saude.class);
                startActivity(i);
            }
        });

        PerfilAnimalViewModel vm = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);
        List<MyItem> compromissos = vm.getItems();

        myAdapter2 = new MyAdapter2(this, compromissos);

        RecyclerView  rvCompromissos = findViewById(R.id.rvCompromissos);
        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        rvCompromissos.setAdapter(myAdapter2);
    }
}