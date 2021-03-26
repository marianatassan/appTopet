package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptopet.R;
import com.example.apptopet.util.HttpRequest;
import com.example.apptopet.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnCadastrar = findViewById(R.id.btnRegistrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNovoLogin =  findViewById(R.id.etNovoLogin);
                final String novoLogin = etNovoLogin.getText().toString();

                EditText etNovaSenha =  findViewById(R.id.etNovaSenha);
                final String novaSenha = etNovaSenha.getText().toString();

                if(novoLogin.isEmpty() || novaSenha.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "Você possui campo(s) não preenchido(s).", Toast.LENGTH_LONG).show();
                    return;
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest("https://servidor-topet-app.herokuapp.com/cadastro.php", "POST", "UTF-8");
                        httpRequest.addParam("novoLogin", novoLogin);
                        httpRequest.addParam("novaSenha", novaSenha);

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
                                        Toast.makeText(CadastroActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });
                            }
                            else {
                                final String error = jsonObject.getString("message");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastroActivity.this, error, Toast.LENGTH_LONG).show();
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
}