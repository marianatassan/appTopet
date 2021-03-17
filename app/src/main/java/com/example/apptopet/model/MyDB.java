package com.example.apptopet.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Animal.class, Compromisso.class, Vaccine.class, Medication.class}, version = 1, exportSchema = false)
public abstract class MyDB extends RoomDatabase {
    private static MyDB INSTANCE;

    public abstract MyDAO myDAO();
    private static Object sLock = new Object();

    public static MyDB getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDB.class, "topet.db").build();
            }
            return INSTANCE;
        }
    }
}


