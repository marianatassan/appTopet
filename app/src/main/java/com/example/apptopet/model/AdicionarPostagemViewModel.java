package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

public class AdicionarPostagemViewModel extends ViewModel {
    String currentPhotoPath = "";

    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }
}
