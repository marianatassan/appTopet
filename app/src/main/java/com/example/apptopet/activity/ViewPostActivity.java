package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptopet.R;
import com.example.apptopet.model.PostagemItem;
import com.example.apptopet.model.ViewPostViewModel;

public class ViewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_social);

        Intent i = getIntent();
        String id_postagem = i.getStringExtra("id_publicacao");

        ViewPostViewModel viewPostViewModel = new ViewModelProvider(this, new ViewPostViewModel.ViewPostagemViewModelFactory(id_postagem)).get(ViewPostViewModel.class);

        LiveData<PostagemItem> postagemItem = viewPostViewModel.getPostagemItem();
        postagemItem.observe(this, new Observer<PostagemItem>() {
            @Override
            public void onChanged(PostagemItem postagemItem) {
                ImageView imvZoom = findViewById(R.id.imvZoom);
                imvZoom.setImageBitmap(postagemItem.getImg());

                TextView tvTitleZoom = findViewById(R.id.tvTitleZoom);
                tvTitleZoom.setText(postagemItem.getTitulo());

                TextView tvDescriptionZoom = findViewById(R.id.tvDescriptionZoom);
                tvDescriptionZoom.setText(postagemItem.getLegenda());
            }
        });
    }
}