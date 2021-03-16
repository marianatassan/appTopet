package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.apptopet.R;
import com.example.apptopet.adapter.ListaCompromissosAdapter;
import com.example.apptopet.adapter.PerfilAnimalAdapter;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.model.ListaCompromissosViewModel;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.PerfilAnimalViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaCompromissosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromissos);

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

        ListaCompromissosViewModel listaCompromissosViewModel = new ViewModelProvider(this, new ListaCompromissosViewModel.ListaCompromissosViewModelFactory(getApplication(), data)).get(ListaCompromissosViewModel.class);
        LiveData<List<Compromisso>> compromissos = listaCompromissosViewModel.getItems();

        final RecyclerView rvCompromissos = findViewById(R.id.rvCompromissos);

        compromissos.observe(this, new Observer<List<Compromisso>>() {
            @Override
            public void onChanged(List<Compromisso> compromissos) {
                ListaCompromissosAdapter listaCompromissosAdapter = new ListaCompromissosAdapter(ListaCompromissosActivity.this, compromissos);
                rvCompromissos.setAdapter(listaCompromissosAdapter);
            }
        });

        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCompromissos.getContext(), DividerItemDecoration.VERTICAL);
        rvCompromissos.addItemDecoration(dividerItemDecoration);
    }
}