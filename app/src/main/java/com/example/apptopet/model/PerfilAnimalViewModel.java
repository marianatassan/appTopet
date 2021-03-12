package com.example.apptopet.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PerfilAnimalViewModel extends ViewModel {

    public void setCompromissos(List<Compromisso> compromissos) {
        this.compromissos = compromissos;
    }

    List<Compromisso> compromissos = new ArrayList<>();

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
}
