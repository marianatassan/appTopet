package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class NovaPostagem extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_postagem);

        NovaPostagemViewModel vm = new ViewModelProvider(this).get(NovaPostagemViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        if(selectPhotoLocation != null) {
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
            imvPhotoPreview.setImageURI(selectPhotoLocation);
        }

        ImageButton imbChooseImage = findViewById(R.id.imbChooseImage);
        imbChooseImage.setOnClickListener(new View.OnClickListener() { // Determina que, ao clicar no botão, uma função será executada;
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() { //Determins que uma função será executada ao clicar no botão;
            @Override
            public void onClick(View v) {
                NovaPostagemViewModel vm = new ViewModelProvider(NovaPostagem.this).get(NovaPostagemViewModel.class);

                Uri selectPhotoLocation = vm.getSelectPhotoLocation();

                if (selectPhotoLocation == null) {
                    Toast.makeText(NovaPostagem.this, "Você precisa selecionar uma imagem", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(NovaPostagem.this, "Você precisa definir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                if (title.length()>15) {
                    Toast.makeText(NovaPostagem.this, "Você ultrapassou o limite de caracteres", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDescription = findViewById(R.id.etDescription);
                String description = etDescription.getText().toString();
                if (description.length() > 600) {
                    Toast.makeText(NovaPostagem.this, "Você ultrapassou o limite de caracteres", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.setData(selectPhotoLocation);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                Uri selectPhotoLocation = data.getData();
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
                imvPhotoPreview.setImageURI(selectPhotoLocation);

                NovaPostagemViewModel vm = new ViewModelProvider(this).get(NovaPostagemViewModel.class);
                vm.setSelectPhotoLocation(selectPhotoLocation);
            }

        }
    }
}