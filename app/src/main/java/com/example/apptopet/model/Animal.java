package com.example.apptopet.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal {
    public String foto;

    public String raca;
    public long dt_nasc;

    @PrimaryKey
    public @NonNull String nomeAnimal;

}
