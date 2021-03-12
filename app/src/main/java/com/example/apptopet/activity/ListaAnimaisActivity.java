package com.example.apptopet.activity;

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

        final ListaAnimaisViewModel vm = new ViewModelProvider(this).get(ListaAnimaisViewModel.class);
        final List<Animal> animais = vm.getItems();

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
                String fotoPerfilAnimal = data.getStringExtra("foto");
                String nome = data.getStringExtra("nome");
                String raca = data.getStringExtra("raca");
                String dt_nasc = data.getStringExtra("dt_nasc");

                final Animal newAnimal = new Animal();
                newAnimal.foto = fotoPerfilAnimal;
                newAnimal.nomeAnimal = nome;
                newAnimal.raca = raca;
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
                ListaAnimaisViewModel vm = new ViewModelProvider(this).get(ListaAnimaisViewModel.class);
                List<Animal> animais = vm.getItems();

                animais.add(newAnimal);

                listaAnimaisAdapter.notifyItemInserted(animais.size()-1);
            }
        }
    }

    public void startPerfilAnimal(Animal animal) {
        Intent i = new Intent(ListaAnimaisActivity.this, PerfilAnimalActivity.class);
        i.putExtra("fotoPerfilAnimal", animal.foto);
        i.putExtra("nomeAnimal", animal.nomeAnimal);
        i.putExtra("raca", animal.raca);
        Date date = new Date(animal.dt_nasc);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        i.putExtra("dt_nasc", strDate);
        startActivity(i);
    }
}
