package com.example.apptopet.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medication {
    public String nome;
    public long dtRemedio;
    public long dtRemedio2;
    public String peso;
    public String nomeAnimalMedication;

    @PrimaryKey(autoGenerate = true)
    public int idMedication;
}
