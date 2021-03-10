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

public class AdicionarAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_animal);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() { //Determins que uma função será executada ao clicar no botão;
            @Override
            public void onClick(View v) {

                EditText etNome = findViewById(R.id.etNome);
                String nome = etNome.getText().toString();
                if (nome == null) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você precisa definir um nome", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etRace = findViewById(R.id.etRace);
                String raca = etRace.getText().toString();
                if (raca == null) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você precisa definir uma raça", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("nome", nome);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}