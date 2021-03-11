package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apptopet.R;
import com.example.apptopet.model.MyItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdicionarAnimalActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    Uri selectedPhotoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_animal);

        final ImageView imvChooseImagePerfil = findViewById(R.id.imvPerfilPreview);
        imvChooseImagePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        /*File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            animal.add(files[i].getAbsolutePath());
        }*/

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNome = findViewById(R.id.etNome);
                String nome = etNome.getText().toString();
                if (nome.isEmpty()) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você precisa definir um nome", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etRace = findViewById(R.id.etRace);
                String raca = etRace.getText().toString();
                if (raca.isEmpty()) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você precisa definir uma raça", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etData = findViewById(R.id.etData);
                String dt_nasc = etData.getText().toString();
                if (dt_nasc.isEmpty()) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você precisa definir uma data de nascimento", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("nome", nome);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                selectedPhotoLocation = data.getData();
                ImageView imvPerfilPreview = findViewById(R.id.imvPerfilPreview);
                imvPerfilPreview.setImageURI(selectedPhotoLocation);
            }
        }
    }
}