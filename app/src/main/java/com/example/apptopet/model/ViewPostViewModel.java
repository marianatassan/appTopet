package com.example.apptopet.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.apptopet.util.HttpRequest;
import com.example.apptopet.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewPostViewModel extends ViewModel{

    MutableLiveData<PostagemItem> postagemItem;
    String id_publicacao;

    public ViewPostViewModel(String id_publicacao) {
        this.id_publicacao = id_publicacao;
    }

    public LiveData<PostagemItem> getPostagemItem() {
        if (this.postagemItem == null) {
            postagemItem= new MutableLiveData<PostagemItem>();
            loadPostagemItem();
        }
        return postagemItem;
    }

    void loadPostagemItem() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest("https://servidor-topet-app.herokuapp.com/get_post_details.php", "GET", "UTF-8");
                httpRequest.addParam("id_publicacao", id_publicacao);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("publicacao");
                        JSONObject jPostagem = jsonArray.getJSONObject(0);

                        String titulo = jPostagem.getString("titulo");
                        String legenda = jPostagem.getString("legenda");

                        String imgBase64 = jPostagem.getString("img");
                        String pureBase64Encoded = imgBase64.substring(imgBase64.indexOf(",") + 1);
                        Bitmap img = Utils.base642Bitmap(pureBase64Encoded);

                        PostagemItem p = new PostagemItem(img, titulo, legenda);
                        postagemItem.postValue(p);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    static public class ViewPostagemViewModelFactory implements ViewModelProvider.Factory {

        String id_postagem;
        public ViewPostagemViewModelFactory(String id_postagem) {
            this.id_postagem = id_postagem;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ViewPostViewModel(id_postagem);         }
    }
}
