package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apptopet.R;
import com.example.apptopet.adapter.ListaAnimaisAdapter;
import com.example.apptopet.model.ListaAnimaisViewModel;
import com.example.apptopet.model.MyItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaAnimaisActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    ListaAnimaisAdapter listaAnimaisAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_animais);

        FloatingActionButton btnAddAnimal = findViewById(R.id.btnAddAnimal);
        btnAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaAnimaisActivity.this, AdicionarAnimalActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        ListaAnimaisViewModel vm = new ViewModelProvider(this).get(ListaAnimaisViewModel.class);
        List<MyItem> animais = vm.getItems();

        listaAnimaisAdapter = new ListaAnimaisAdapter(this, animais);

        RecyclerView rvListaAnimais = findViewById(R.id.rvListaAnimais);
        rvListaAnimais.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListaAnimais.setLayoutManager(layoutManager);

        rvListaAnimais.setAdapter(listaAnimaisAdapter);

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

                ListaAnimaisViewModel vm = new ViewModelProvider(this).get(ListaAnimaisViewModel.class);
                List<MyItem> animais = vm.getItems();

                animais.add(novoAnimal);

                listaAnimaisAdapter.notifyItemInserted(animais.size()-1);
            }
        }
    }

    public void startPerfilAnimal(MyItem animais) {
        Intent i = new Intent(ListaAnimaisActivity.this, PerfilAnimalActivity.class);
        startActivity(i);
    }
}