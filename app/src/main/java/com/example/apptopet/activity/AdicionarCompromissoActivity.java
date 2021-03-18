package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apptopet.R;
import com.example.apptopet.model.AdicionarAnimalViewModel;
import com.example.apptopet.util.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AdicionarCompromissoActivity extends AppCompatActivity {
    String nomeAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compromisso);

        Button btnAddCompromisso = findViewById(R.id.btnAddCompromisso);
        btnAddCompromisso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etDescricaoCompromisso = findViewById(R.id.etDescricaoCompromisso);
                String descricao = etDescricaoCompromisso.getText().toString();
                if (descricao.isEmpty()) {
                    Toast.makeText(AdicionarCompromissoActivity.this, "Você precisa definir uma descrição.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (descricao.length()>20) {
                    Toast.makeText(AdicionarCompromissoActivity.this, "Você ultrapassou o limite de 20 caracteres na descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDataCompromisso = findViewById(R.id.etDataCompromisso);
                String data = etDataCompromisso.getText().toString();
                if (data.isEmpty()) {
                    Toast.makeText(AdicionarCompromissoActivity.this, "Você precisa definir uma data.", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("descricao", descricao);
                i.putExtra("data", data);
                i.putExtra("nomeAnimal", nomeAnimal);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}