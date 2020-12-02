package com.example.apptopet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Saude extends AppCompatActivity {
    MyAdapter3 myAdapter3;
    MyAdapter4 myAdapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);

        VacinaViewModel vm = new ViewModelProvider(this).get(VacinaViewModel.class);
        List<MyItem > vacinas = vm.getItems();

        myAdapter3 = new MyAdapter3(this, vacinas);

        RecyclerView rvVacinas = findViewById(R.id.rvVacinas);
        rvVacinas.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvVacinas.setLayoutManager(layoutManager);

        rvVacinas.setAdapter(myAdapter3);

        RemedioViewModel vm2 = new ViewModelProvider(this).get(RemedioViewModel.class);
        List<MyItem > remedios = vm.getItems();

        myAdapter4 = new MyAdapter4(this, remedios);

        RecyclerView rvRemedios = findViewById(R.id.rvRemedios);
        rvRemedios.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        rvRemedios.setLayoutManager(layoutManager2);

        rvRemedios.setAdapter(myAdapter4);
    }
}