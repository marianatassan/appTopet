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
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptopet.R;
import com.example.apptopet.adapter.ListaAnimaisAdapter;
import com.example.apptopet.adapter.PerfilAnimalAdapter;
import com.example.apptopet.model.Animal;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.model.ListaAnimaisViewModel;
import com.example.apptopet.model.MyDAO;
import com.example.apptopet.model.MyDB;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.PerfilAnimalViewModel;
import com.example.apptopet.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PerfilAnimalActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        Intent i = getIntent();
        String fotoPerfilAnimal = i.getStringExtra("fotoPerfilAnimal");
        final String nomeAnimal = i.getStringExtra("nomeAnimal");
        String raca = i.getStringExtra("raca");
        String especie = i.getStringExtra("especie");
        String dt_nasc = i.getStringExtra("dt_nasc");

        //PerfilAnimalViewModel vm = new ViewModelProvider(this).get(PerfilAnimalViewModel.class);

        //Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        Bitmap bmp = Utils.getBitmap(fotoPerfilAnimal);
        ImageView imvProfile = findViewById(R.id.imvProfile);
        imvProfile.setImageBitmap(bmp);

        final TextView tvNomeAnimal = findViewById(R.id.tvNomeAnimal);
        tvNomeAnimal.setText(nomeAnimal);

        TextView tvRaca = findViewById(R.id.tvRaca);
        tvRaca.setText(raca);

        TextView tvEspecie = findViewById(R.id.tvEspecie);
        tvEspecie.setText(especie);

        TextView tvDtNasc = findViewById(R.id.tvDtNascimento);
        tvDtNasc.setText(dt_nasc);

        FloatingActionButton floatingActionButton = findViewById(R.id.fabtnPerfil);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilAnimalActivity.this, AdicionarCompromissoActivity.class);
                i.putExtra("nomeAnimal", nomeAnimal);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        Button btnSaude = findViewById(R.id.btnSaude);
        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilAnimalActivity.this, SaudeActivity.class);
                startActivity(i);
            }
        });
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);
        long data = 0;
        try {
            Date data2 = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
            data = data2.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PerfilAnimalViewModel perfilAnimalViewModel = new ViewModelProvider(this, new PerfilAnimalViewModel.PerfilAnimalViewModelFactory(getApplication(), nomeAnimal, data)).get(PerfilAnimalViewModel.class);
        LiveData<List<Compromisso>> compromissos = perfilAnimalViewModel.getItems();

        final RecyclerView rvCompromissos = findViewById(R.id.rvCompromissos);

        compromissos.observe(this, new Observer<List<Compromisso>>() {
            @Override
            public void onChanged(List<Compromisso> compromissos) {
                PerfilAnimalAdapter perfilAnimalAdapter = new PerfilAnimalAdapter(PerfilAnimalActivity.this, compromissos);
                rvCompromissos.setAdapter(perfilAnimalAdapter);
            }
        });

        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCompromissos.getContext(), DividerItemDecoration.VERTICAL);
        rvCompromissos.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String descricao = data.getStringExtra("descricao");
                String dataCompromisso = data.getStringExtra("data");
                Intent intent = getIntent();
                String nomeAnimal = intent.getStringExtra("nomeAnimal");

                final Compromisso newCompromisso = new Compromisso();
                newCompromisso.descricao = descricao;
                newCompromisso.nomeAnimal = nomeAnimal;
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataCompromisso);
                    newCompromisso.data = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        MyDAO myDAO = MyDB.getInstance(getApplication()).myDAO();
                        myDAO.insertCompromisso(newCompromisso);
                    }
                });
            }
        }
    }
}