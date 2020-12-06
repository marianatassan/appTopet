package com.example.apptopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Compromissos extends AppCompatActivity {

    CompromissosAdapter compromissosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromissos);

        CompromissoViewModel vm = new ViewModelProvider(this).get(CompromissoViewModel.class);
        List<MyItem> compromissos2 = vm.getItems();

        compromissosAdapter = new CompromissosAdapter(this, compromissos2);

        RecyclerView rvCompromissos = findViewById(R.id.rvCompromissos);
        rvCompromissos.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCompromissos.setLayoutManager(layoutManager);

        rvCompromissos.setAdapter(compromissosAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCompromissos.getContext(), DividerItemDecoration.VERTICAL);
        rvCompromissos.addItemDecoration(dividerItemDecoration);
    }
}