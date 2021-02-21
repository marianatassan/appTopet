package com.example.apptopet;

import androidx.lifecycle.ViewModel;

public class SaudeViewModel extends ViewModel {
    int navigationOptionSelected = R.id.opVaccines;

    public int getNavigationOptionSelected() {
        return navigationOptionSelected;
    }

    public void setNavigationOptionSelected(int navigationOptionSelected) {
        this.navigationOptionSelected = navigationOptionSelected;
    }
}
