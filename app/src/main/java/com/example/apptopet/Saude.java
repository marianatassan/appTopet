package com.example.apptopet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Saude extends AppCompatActivity {
    MyAdapter3 myAdapter3;
    MyAdapter4 myAdapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);

        VaccinesFragment vaccinesFragment = VaccinesFragment.newInstance();
        setFragment(vaccinesFragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.btnNavSaude);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opVaccines:
                        VaccinesFragment vaccinesFragment = VaccinesFragment.newInstance();
                        setFragment(vaccinesFragment);
                        break;
                    case R.id.opMedications:
                        MedicationsFragment medicationsFragment = MedicationsFragment.newInstance();
                        setFragment(medicationsFragment);
                        break;
                }
                return true;
            }
        });
    }

    void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flSaude, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}