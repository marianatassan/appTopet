package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.apptopet.R;
import com.example.apptopet.adapter.ListaAnimaisAdapter;
import com.example.apptopet.model.Animal;
import com.example.apptopet.model.ListaAnimaisViewModel;
import com.example.apptopet.model.MyDAO;
import com.example.apptopet.model.MyDB;
import com.example.apptopet.model.MyItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class
 ListaAnimaisActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

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
        LiveData<List<Animal>> animais = vm.getItems();

        final RecyclerView rvListaAnimais = findViewById(R.id.rvListaAnimais);

        animais.observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animals) {
                ListaAnimaisAdapter listaAnimaisAdapter = new ListaAnimaisAdapter(ListaAnimaisActivity.this, animals);
                rvListaAnimais.setAdapter(listaAnimaisAdapter);
            }
        });


        rvListaAnimais.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListaAnimais.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvListaAnimais.getContext(), DividerItemDecoration.VERTICAL);
        rvListaAnimais.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String fotoPerfilAnimal = data.getStringExtra("foto");
                String nome = data.getStringExtra("nome");
                String raca = data.getStringExtra("raca");
                String especie = data.getStringExtra("especie");
                String dt_nasc = data.getStringExtra("dt_nasc");

                final Animal newAnimal = new Animal();
                newAnimal.foto = fotoPerfilAnimal;
                newAnimal.nomeAnimal = nome;
                newAnimal.raca = raca;
                newAnimal.especie = especie;
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dt_nasc);
                    newAnimal.dt_nasc = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        MyDAO myDAO = MyDB.getInstance(getApplication()).myDAO();
                        myDAO.insertAnimal(newAnimal);
                    }
                });
            }
        }
    }

    public void startPerfilAnimal(Animal animal) {
        Intent i = new Intent(ListaAnimaisActivity.this, PerfilAnimalActivity.class);
        i.putExtra("fotoPerfilAnimal", animal.foto);
        i.putExtra("nomeAnimal", animal.nomeAnimal);
        i.putExtra("raca", animal.raca);
        i.putExtra("especie", animal.especie);
        Date date = new Date(animal.dt_nasc);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        i.putExtra("dt_nasc", strDate);
        startActivity(i);
    }
}
