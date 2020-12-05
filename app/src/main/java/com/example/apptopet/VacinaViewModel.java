package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VacinaViewModel extends ViewModel {
    List<MyItem> vacinas = new ArrayList<>();

    public VacinaViewModel() {
        MyItem vacina1 = new MyItem();
        vacina1.nomeVacina = "Vacina A";
        vacina1.dtVacina = "Data: 25/05/2020";
        vacina1.dtRevacina = "Revacinação: 25/05/2020";
        vacina1.pesoVacina = "Peso: 2kg";

        MyItem vacina2 = new MyItem();
        vacina2.nomeVacina = "Vacina B";
        vacina2.dtVacina = "Data: 25/05/2020";
        vacina2.dtRevacina = "Revacinação: 25/05/2020";
        vacina2.pesoVacina = "Peso: 5kg";

        MyItem vacina3 = new MyItem();
        vacina3.nomeVacina = "Vacina C";
        vacina3.dtVacina = "Data: 25/05/2020";
        vacina3.dtRevacina = "Revacinação: 25/05/2020";
        vacina3.pesoVacina= "Peso: 6kg";

        MyItem vacina4 = new MyItem();
        vacina4.nomeVacina = "Vacina D";
        vacina4.dtVacina = "Data: 25/05/2020";
        vacina4.dtRevacina = "Revacinação: 25/05/2020";
        vacina4.pesoVacina = "Peso: 9kg";

        MyItem vacina5 = new MyItem();
        vacina5.nomeVacina = "Vacina E";
        vacina5.dtVacina = "Data: 25/05/2020";
        vacina5.dtRevacina = "Revacinação: 25/05/2020";
        vacina5.pesoVacina = "Peso: 9kg";

        vacinas.add(vacina1);
        vacinas.add(vacina2);
        vacinas.add(vacina3);
        vacinas.add(vacina4);
        vacinas.add(vacina5);

    }

    public List<MyItem> getItems() { return vacinas;}
}
