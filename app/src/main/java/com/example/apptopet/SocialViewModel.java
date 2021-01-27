package com.example.apptopet;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SocialViewModel extends ViewModel {
    List<MyItem> photos = new ArrayList<>();

    Uri selectPhotoLocation = null;

    public Uri getSelectPhotoLocation() {
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) {
        this.selectPhotoLocation = selectPhotoLocation;
    }
    public List<MyItem> getItems() {
        return photos;
    }
}
