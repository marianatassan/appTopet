package com.example.apptopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Saude extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);

        final SaudeViewModel vModel = new ViewModelProvider(this).get(SaudeViewModel.class);

        bottomNavigationView = findViewById(R.id.btnNavSaude);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opVaccines:
                        VaccinesFragment vaccinesFragment = VaccinesFragment.newInstance();
                        setFragment(vaccinesFragment);
                        vModel.setNavigationOptionSelected(R.id.opVaccines);
                        break;
                    case R.id.opMedications:
                        MedicationsFragment medicationsFragment = MedicationsFragment.newInstance();
                        setFragment(medicationsFragment);
                        vModel.setNavigationOptionSelected(R.id.opMedications);
                        break;
                }
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(vModel.getNavigationOptionSelected());
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flSaude, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}