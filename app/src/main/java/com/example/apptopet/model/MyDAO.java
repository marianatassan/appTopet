package com.example.apptopet.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract class MyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public void insertAnimal(Animal animal);

    @Query("SELECT * FROM Animal ORDER BY nomeAnimal")
    abstract public LiveData<List<Animal>> getAnimais();

    @Query("SELECT * FROM Compromisso WHERE data >= :dataAtual ORDER BY data ASC")
    abstract public LiveData<List<Compromisso>> getCompromissos(long dataAtual);

    @Query("SELECT id, descricao, data FROM Compromisso WHERE nomeAnimal = :nomeAnimal AND data >= :dataAtual ORDER BY data ASC")
    abstract public LiveData<List<Compromisso>> getCompromissosAnimal(String nomeAnimal, long dataAtual);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public void insertCompromisso(Compromisso compromisso);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public void insertVaccine(Vaccine vaccine);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract public void insertMedication(Medication medication);

    @Query("SELECT * FROM Vaccine WHERE nomeAnimalVaccine = :nomeAnimalVaccine ORDER BY dtRevacina ASC")
    abstract public LiveData<List<Vaccine>> getVaccinesAnimal(String nomeAnimalVaccine);

    @Query("SELECT * FROM Medication WHERE nomeAnimalMedication = :nomeAnimalMedication ORDER BY dtRemedio2 ASC")
    abstract public LiveData<List<Medication>> getMedicationsAnimal(String nomeAnimalMedication);
}


