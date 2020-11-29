package com.example.apptopet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TelaPrincipal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_tela);

        Button btnAnimais = findViewById(R.id.btnAnimais);
        btnAnimais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaPrincipal.this, AnimaisLista.class);
                startActivity(i);
            }
        });

        Button btnSocial = findViewById(R.id.btnSocial);
        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaPrincipal.this, Social.class);
                startActivity(i);
            }
        });

    }
}
