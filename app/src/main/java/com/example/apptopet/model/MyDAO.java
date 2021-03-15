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

    @Query("SELECT * FROM Animal")
    abstract public LiveData<List<Animal>> getAnimais();

    @Query("SELECT * FROM Compromisso WHERE data > :dataAtual ORDER BY data ASC")
    abstract public List<Compromisso> getCompromissos(long dataAtual);

    @Query("SELECT id, descricao, data FROM Compromisso WHERE nomeAnimal = :nomeAnimal AND data > :dataAtual ORDER BY data ASC")
    abstract public List<Compromisso> getCompromissosAnimal(String nomeAnimal, long dataAtual);

    @Insert
    abstract public void insertCompromisso(Compromisso compromisso);
}


