package com.example.apptopet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Compromisso {
    public String descricao;
    public long data;
    public String nomeAnimal;

    @PrimaryKey(autoGenerate = true)
    public int id;

}
