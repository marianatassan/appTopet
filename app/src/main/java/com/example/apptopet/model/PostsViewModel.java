package com.example.apptopet.model;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;
import com.example.apptopet.util.HttpRequest;
import com.example.apptopet.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PostsViewModel extends ViewModel {

    MutableLiveData<List<PostagemItem>> postagemItems;

    public MutableLiveData<List<PostagemItem>> getPostagemItems() {
        if (postagemItems == null) {
            postagemItems = new MutableLiveData<List<PostagemItem>>();
            loadPostagemItems();
        }
        return postagemItems;
    }

    public void refreshPostagens() { // função que chama a função que carrega os produtos do servidor;
        loadPostagemItems();
    }

    void loadPostagemItems () {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<PostagemItem> postagemList = new ArrayList<>();

                HttpRequest httpRequest = new HttpRequest("https://servidor-topet-app.herokuapp.com/get_all_posts.php", "GET", "UTF-8");
                try {
                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("publicacoes");
                        for (int i = 0; i<jsonArray.length(); i++) {
                            JSONObject jPostagem = jsonArray.getJSONObject(i);

                            String id_publicacao = jPostagem.getString("id_publicacao");
                            String titulo = jPostagem.getString("titulo");
                            String imgBase64 = jPostagem.getString("img");
                            String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                            Bitmap img = Utils.base642Bitmap(pureBase64Encoded);

                            PostagemItem postagemItem = new PostagemItem(id_publicacao, titulo, img);
                            postagemList.add(postagemItem);
                        }
                        postagemItems.postValue(postagemList);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
