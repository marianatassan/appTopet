package com.example.apptopet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ZoomSocial extends AppCompatActivity {

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