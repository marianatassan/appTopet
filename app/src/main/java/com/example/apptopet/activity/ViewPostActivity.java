package com.example.apptopet.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptopet.R;
import com.example.apptopet.model.MyItem;

public class ViewPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_social);

        MyItem newZoom = new MyItem();
        newZoom.postagem = R.drawable.calopsita;
        ImageView imvZoom = findViewById(R.id.imvZoom);
        imvZoom.setImageResource(newZoom.postagem);

        newZoom.tituloFix = "Kevin";
        TextView tvTitle = findViewById(R.id.tvTitleZoom);
        tvTitle.setText(newZoom.tituloFix);

        newZoom.description = "Oi amigos! Olha como eu sou um gatão! Hoje tive um dia de spa, tomei banho e cortei as unhas e as asas";
        TextView tvDescriptionZoom = findViewById(R.id.tvDescriptionZoom);
        tvDescriptionZoom.setText(newZoom.description);
    }
}