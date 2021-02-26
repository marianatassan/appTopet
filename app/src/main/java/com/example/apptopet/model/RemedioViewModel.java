package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class RemedioViewModel extends ViewModel {
    List<MyItem> remedios = new ArrayList<>();

    public RemedioViewModel() {
        MyItem remedio1 = new MyItem();
        remedio1.nomeRemedio = "Remedio A";
        remedio1.dtRemedio = "25/05/2020";
        remedio1.dtRemedio2 = "25/05/2020";
        remedio1.pesoRemedio = "5kg";

        MyItem remedio2 = new MyItem();
        remedio2.nomeRemedio = "Remedio B";
        remedio2.dtRemedio = "25/05/2020";
        remedio2.dtRemedio2 = "25/05/2020";
        remedio2.pesoRemedio = "8kg";

        remedios.add(remedio1);
        remedios.add(remedio2);
    }
    public List<MyItem> getItems() { return remedios;}
}
