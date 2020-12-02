package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VacinaViewModel extends ViewModel {
    List<MyItem> vacinas = new ArrayList<>();

    public VacinaViewModel() {
        MyItem vacina1 = new MyItem();
        vacina1.nomeVacina = "Vacina A";
        vacina1.dtVacina = "25/05/2020";
        vacina1.dtRevacina = "25/05/2020";
        vacina1.pesoVacina = "2kg";

        MyItem vacina2 = new MyItem();
        vacina2.nomeVacina = "Vacina B";
        vacina2.dtVacina = "25/05/2020";
        vacina2.dtRevacina = "25/05/2020";
        vacina2.pesoVacina = "5kg";

        MyItem vacina3 = new MyItem();
        vacina3.nomeVacina = "Vacina C";
        vacina3.dtVacina = "25/05/2020";
        vacina3.dtRevacina = "25/05/2020";
        vacina3.pesoVacina= "6kg";

        MyItem vacina4 = new MyItem();
        vacina4.nomeVacina = "Vacina D";
        vacina4.dtVacina = "25/05/2020";
        vacina4.dtRevacina = "25/05/2020";
        vacina4.pesoVacina = "9kg";

        vacinas.add(vacina1);
        vacinas.add(vacina2);
        vacinas.add(vacina3);
        vacinas.add(vacina4);

    }

    public List<MyItem> getItems() { return vacinas;}
}
