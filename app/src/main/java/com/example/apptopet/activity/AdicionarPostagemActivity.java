package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptopet.R;
import com.example.apptopet.model.AdicionarPostagemViewModel;
import com.example.apptopet.util.Config;
import com.example.apptopet.util.HttpRequest;
import com.example.apptopet.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class  AdicionarPostagemActivity extends AppCompatActivity {
    static int RESULT_TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_postagem);

        final AdicionarPostagemViewModel adicionarPostagemViewModel = new ViewModelProvider(this).get(AdicionarPostagemViewModel.class);
        String currentPhotoPath = adicionarPostagemViewModel.getCurrentPhotoPath();
        if (!currentPhotoPath.isEmpty()) {
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
            Bitmap bitmap = Utils.getBitmap(currentPhotoPath, imvPhotoPreview.getWidth(), imvPhotoPreview.getHeight());
            imvPhotoPreview.setImageBitmap(bitmap);
        }

        ImageView imbChooseImage = findViewById(R.id.imvPhotoPreview);
        imbChooseImage.setOnClickListener(new View.OnClickListener() { // Determina que, ao clicar no botão, uma função será executada;
            @Override
            public void onClick(View v) { dispatchTakePictureIntent(); }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() { //Determins que uma função será executada ao clicar no botão;
            @Override
            public void onClick(View v) {
                v.setEnabled(false);

                EditText etTitle = findViewById(R.id.etTitle);
                final String title = etTitle.getText().toString();

                if (title.isEmpty()) {
                    Toast.makeText(AdicionarPostagemActivity.this, "Você precisa definir um título", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }
                if (title.length()>15) {
                    Toast.makeText(AdicionarPostagemActivity.this, "Você ultrapassou o limite de caracteres", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                final EditText etDescription = findViewById(R.id.etDescription);
                final String description = etDescription.getText().toString();
                if (description.length() > 600) {
                    Toast.makeText(AdicionarPostagemActivity.this, "Você ultrapassou o limite de caracteres", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                final String currentPhotoPath = adicionarPostagemViewModel.getCurrentPhotoPath();
                if (currentPhotoPath.isEmpty()) {
                    Toast.makeText(AdicionarPostagemActivity.this, "O campo não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }
                final String login = Config.getLogin(AdicionarPostagemActivity.this);
                final String senha = Config.getSenha(AdicionarPostagemActivity.this);
                final String id_usuario = Config.getIdUsario(AdicionarPostagemActivity.this);

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest("https://servidor-topet-app.herokuapp.com/create_post.php", "POST", "UTF-8");
                        httpRequest.addParam("titulo", title);
                        httpRequest.addParam("legenda", description);
                        httpRequest.addFile("img", new File(currentPhotoPath));
                        httpRequest.addParam("login", login);
                        httpRequest.addParam("senha", senha);
                        httpRequest.addParam("id_usuario", id_usuario);

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Utils.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            final int success = jsonObject.getInt("success");
                            if(success == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AdicionarPostagemActivity.this, "Postagem publicada com sucesso", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("message");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AdicionarPostagemActivity.this, error, Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File f = null;
        try {
            f = createImageFile();
        } catch (IOException e) {
            Toast.makeText(AdicionarPostagemActivity.this, "Não foi possível criar o arquivo.", Toast.LENGTH_LONG).show();
            return;
        }

        AdicionarPostagemViewModel adicionarPostagemViewModel = new ViewModelProvider(this).get(AdicionarPostagemViewModel.class);
        adicionarPostagemViewModel.setCurrentPhotoPath(f.getAbsolutePath());


        if (f != null) {
            Uri fUri = FileProvider.getUriForFile(AdicionarPostagemActivity.this, "con.example.apptopet.fileprovider", f);
            i.putExtra(MediaStore.EXTRA_OUTPUT,fUri);
            startActivityForResult(i, RESULT_TAKE_PICTURE);
        }
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
        if (requestCode == RESULT_TAKE_PICTURE) {
            AdicionarPostagemViewModel adicionarPostagemViewModel = new ViewModelProvider(this).get(AdicionarPostagemViewModel.class);
            String currentPhotoPath = adicionarPostagemViewModel.getCurrentPhotoPath();
            if (resultCode == Activity.RESULT_OK) {
                try {
                    Utils.scaleImage(currentPhotoPath, 2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
                Bitmap bitmap = Utils.getBitmap(currentPhotoPath, imvPhotoPreview.getWidth(), imvPhotoPreview.getHeight());
                imvPhotoPreview.setImageBitmap(bitmap);
            }
            else {
                File f = new File(currentPhotoPath);
                f.delete();
                adicionarPostagemViewModel.setCurrentPhotoPath("");
            }
        }
    }
}