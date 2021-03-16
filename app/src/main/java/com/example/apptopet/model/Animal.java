package com.example.apptopet.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal {
    public String foto;

    public String raca;
    public String especie;
    public long dt_nasc;

    @PrimaryKey @NonNull
    public String nomeAnimal;

}
