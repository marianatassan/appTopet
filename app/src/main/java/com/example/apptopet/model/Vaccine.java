package com.example.apptopet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vaccine {
    public String nome;
    public long dtVacina;
    public long dtRevacina;
    public String peso;
    public String nomeAnimalVaccine;

    @PrimaryKey(autoGenerate = true)
    public int idVaccine;
}
