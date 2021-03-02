package com.example.apptopet.model;

import android.graphics.Bitmap;

public class PostagemItem {
    String id_postagem;
    String titulo;
    String legenda;
    Bitmap img;

    public PostagemItem(Bitmap img, String titulo, String legenda) {
        this.titulo = titulo;
        this.legenda = legenda;
        this.img = img;
    }

    public PostagemItem(String id_postagem, String titulo, Bitmap img) {
        this.id_postagem = id_postagem;
        this.titulo = titulo;
        this.img = img;
    }

    public String getId_postagem() {
        return id_postagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLegenda() {
        return legenda;
    }

    public Bitmap getImg() {
        return img;
    }
}
