package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptopet.R;

public class AddVaccineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        Button btnAddVaccine = findViewById(R.id.btnAddVaccine);
        btnAddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNomeVacina = findViewById(R.id.etNomeVacina);
                String nome = etNomeVacina.getText().toString();

                EditText etDataVacina = findViewById(R.id.etDataVacina);
                String dataVacina = etDataVacina.getText().toString();

                EditText etDataRevacina = findViewById(R.id.etDataRevacina);
                String dataRevacina = etDataRevacina.getText().toString();

                EditText etPesoVacina = findViewById(R.id.etPesoVacina);
                String peso = etPesoVacina.getText().toString();

                if (nome.isEmpty() || dataVacina.isEmpty() || dataRevacina.isEmpty() || peso.isEmpty()) {
                    Toast.makeText(AddVaccineActivity.this, "Você possui campo(s) não preenchido(s)", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("nome", nome);
                i.putExtra("dataVacina", dataVacina);
                i.putExtra("dataRevacina", dataRevacina);
                i.putExtra("peso", peso);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}