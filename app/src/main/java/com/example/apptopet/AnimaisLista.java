package com.example.apptopet;

import androidx.annotation.Dimension;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AnimaisLista extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_animais);

        FloatingActionButton btnAddAnimal = findViewById(R.id.btnAddAnimal);
        btnAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnimaisLista.this, AdicionarAnimal.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        AnimaisListaViewModel vm = new ViewModelProvider(this).get(AnimaisListaViewModel.class);
        List<MyItem> animais = vm.getItems();

        myAdapter = new MyAdapter(this, animais);

        RecyclerView rvListaAnimais = findViewById(R.id.rvListaAnimais);
        rvListaAnimais.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListaAnimais.setLayoutManager(layoutManager);

        rvListaAnimais.setAdapter(myAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvListaAnimais.getContext(), DividerItemDecoration.VERTICAL);
        rvListaAnimais.addItemDecoration(dividerItemDecoration);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String nome = data.getStringExtra("nome");

                MyItem novoAnimal = new MyItem();
                novoAnimal.nome = nome;

                AnimaisListaViewModel vm = new ViewModelProvider(this).get(AnimaisListaViewModel.class); //new ViewModelProvider(this).get(AnimaisListaViewModel.class); // Cria um ViewModel Provider referente a MainActivity;
                List<MyItem> animais = vm.getItems();

                animais.add(novoAnimal);

                myAdapter.notifyItemInserted(animais.size()-1);
            }
        }
    }

    public void startPerfilAnimal(MyItem animais) {
        Intent i = new Intent(AnimaisLista.this, PerfilAnimal.class);
        //i.putExtra("photo_path", animais);
        startActivity(i);
    }
}
