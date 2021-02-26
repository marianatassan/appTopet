package com.example.apptopet.model;

import android.graphics.Bitmap;

public class PostagemItem {
    String id_postagem;
    String titulo;
    String legenda;
    Bitmap img;


    public PostagemItem(String pid, String name) {
        this.id_postagem = id_postagem;
        this.titulo = titulo;
        this.img = img;
    }

    public PostagemItem(String titulo, String legenda, Bitmap img) {
        this.titulo = titulo;
        this.legenda = legenda;
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
