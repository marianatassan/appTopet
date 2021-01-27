package com.example.apptopet;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NovaPostagemViewModel extends ViewModel {
    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
