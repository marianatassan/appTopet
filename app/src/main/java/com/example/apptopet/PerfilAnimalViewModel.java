package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PerfilAnimalViewModel extends ViewModel {
    List<MyItem2> compromissos = new ArrayList<>();

    public PerfilAnimalViewModel() {
        MyItem2 compromisso1 = new MyItem2();
        compromisso1.compromisso = "Escovação";
        compromisso1.data = "25/05/2020";

        MyItem2 compromisso2 = new MyItem2();
        compromisso2.compromisso = "Veterinário";
        compromisso2.data = "25/05/2020";

        MyItem2 compromisso3 = new MyItem2();
        compromisso3.compromisso = "Cortar Unhas";
        compromisso3.data = "25/05/2020";

        MyItem2 compromisso4 = new MyItem2();
        compromisso4.compromisso = "Banho";
        compromisso4.data = "25/05/2020";

        MyItem2 compromisso5 = new MyItem2();
        compromisso5.compromisso = "Tosagem";
        compromisso5.data = "25/05/2020";

        compromissos.add(compromisso1);
        compromissos.add(compromisso2);
        compromissos.add(compromisso3);
        compromissos.add(compromisso4);
        compromissos.add(compromisso5);
    }

    public List<MyItem2> getItems() {
        return compromissos;
    }
}
