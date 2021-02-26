package com.example.apptopet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptopet.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_tela);

        Button btnAnimais = findViewById(R.id.btnAnimais);
        btnAnimais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ListaAnimaisActivity.class);
                startActivity(i);
            }
        });

        Button btnSocial = findViewById(R.id.btnSocial);
        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, PostsActivity.class);
                startActivity(i);
            }
        });

        Button btnCompromissos = findViewById(R.id.btnCompromissos);
        btnCompromissos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ListaCompromissosActivity.class);
                startActivity(i);
            }
        });
    }
}
