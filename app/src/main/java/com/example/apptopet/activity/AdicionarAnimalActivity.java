package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdicionarAnimalActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    String fotoPerfilAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_animal);

        AdicionarAnimalViewModel vm = new ViewModelProvider(this).get(AdicionarAnimalViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();

        if(selectPhotoLocation != null) {
            ImageView imvPhotoPreview = findViewById(R.id.imvPerfilPreview);
            imvPhotoPreview.setImageURI(selectPhotoLocation);
        }

        final ImageView imvChooseImagePerfil = findViewById(R.id.imvPerfilPreview);
        imvChooseImagePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNome = findViewById(R.id.etNome);
                String nome = etNome.getText().toString();

                EditText etRace = findViewById(R.id.etRace);
                String raca = etRace.getText().toString();

                EditText etEspecie = findViewById(R.id.etEspecie);
                String especie = etEspecie.getText().toString();

                EditText etData = findViewById(R.id.etData);
                String dt_nasc = etData.getText().toString();

                if (nome.isEmpty() || raca.isEmpty() || especie.isEmpty() || dt_nasc.isEmpty() || fotoPerfilAnimal.isEmpty()) {
                    Toast.makeText(AdicionarAnimalActivity.this, "Você possui campo(s) não preenchido(s)", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("foto", fotoPerfilAnimal);
                i.putExtra("nome", nome);
                i.putExtra("raca", raca);
                i.putExtra("especie", especie);
                i.putExtra("dt_nasc", dt_nasc);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName,".jpg", storageDir);
        return f;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectPhotoLocation = data.getData();
                try {
                    Bitmap bmp = Utils.getBitmap(AdicionarAnimalActivity.this, selectPhotoLocation, 1);
                    File f = createImageFile();
                    Utils.saveImage(bmp, f.getAbsolutePath(), 1);
                    fotoPerfilAnimal = f.getAbsolutePath();
                    ImageView imvPhotoPreview = findViewById(R.id.imvPerfilPreview);
                    imvPhotoPreview.setImageURI(selectPhotoLocation);

                    AdicionarAnimalViewModel vm = new ViewModelProvider(this).get(AdicionarAnimalViewModel.class);
                    vm.setSelectPhotoLocation(selectPhotoLocation);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}