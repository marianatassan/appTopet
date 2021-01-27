package com.example.apptopet;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SocialViewModel extends ViewModel {
    List<MyItem> photos = new ArrayList<>();

    public List<MyItem> getItems() {
        return photos;
    }
}
