package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import com.example.apptopet.R;

public class SaudeViewModel extends ViewModel {
    int navigationOptionSelected = R.id.opVaccines;

    public int getNavigationOptionSelected() {
        return navigationOptionSelected;
    }

    public void setNavigationOptionSelected(int navigationOptionSelected) {
        this.navigationOptionSelected = navigationOptionSelected;
    }
}
