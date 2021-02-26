package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.apptopet.R;
import com.example.apptopet.adapter.ListaCompromissosAdapter;
import com.example.apptopet.model.ListaCompromissosViewModel;
import com.example.apptopet.model.MyItem;

import java.util.List;

public class ListaCompromissosActivity extends AppCompatActivity {

    ListaCompromissosAdapter listaCompromissosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromissos);

        ListaCompromissosViewModel vm = new ViewModelProvider(this).get(ListaCompromissosViewModel.class);
        List<MyItem> compromissos2 = vm.getItems();

        listaCompromissosAdapter = new ListaCompromissosAdapter(this, compromissos2);

        RecyclerView rvCompromissos = findViewById(R.id.rvCompromissos);
        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        rvCompromissos.setAdapter(listaCompromissosAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCompromissos.getContext(), DividerItemDecoration.VERTICAL);
        rvCompromissos.addItemDecoration(dividerItemDecoration);
    }
}