package com.example.apptopet.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {
    public static void setIdUsuario(Context context, String id) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("id", id).commit();
    }

    public static String getIdUsario(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("id","");
    }

    public static void setLogin(Context context, String login) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("login", login).commit();
    }

    public static String getLogin(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("login", "");
    }

    public static void setSenha(Context context, String senha) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putString("senha", senha).commit();
    }

    public static String getSenha(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("configs", 0);
        return mPrefs.getString("senha", "");
    }
}
