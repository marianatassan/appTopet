package com.example.apptopet;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PerfilAnimalViewModel extends ViewModel {
    List<MyItem> compromissos = new ArrayList<>();

    public PerfilAnimalViewModel() {
        MyItem compromisso1 = new MyItem();
        compromisso1.compromisso = "Escovação";
        compromisso1.data = "25/05/2020";

        MyItem compromisso2 = new MyItem();
        compromisso2.compromisso = "Veterinário";
        compromisso2.data = "25/05/2020";

        MyItem compromisso3 = new MyItem();
        compromisso3.compromisso = "Cortar Unhas";
        compromisso3.data = "25/05/2020";

        MyItem compromisso4 = new MyItem();
        compromisso4.compromisso = "Banho";
        compromisso4.data = "25/05/2020";

        MyItem compromisso5 = new MyItem();
        compromisso5.compromisso = "Tosagem";
        compromisso5.data = "25/05/2020";

        MyItem compromisso6 = new MyItem();
        compromisso6.compromisso = "Compromisso 6";
        compromisso6.data = "25/05/2020";

        MyItem compromisso7 = new MyItem();
        compromisso7.compromisso = "Compromisso 7";
        compromisso7.data = "25/05/2020";

        compromissos.add(compromisso1);
        compromissos.add(compromisso2);
        compromissos.add(compromisso3);
        compromissos.add(compromisso4);
        compromissos.add(compromisso5);
        compromissos.add(compromisso6);
        compromissos.add(compromisso7);
    }

    public List<MyItem> getItems() {
        return compromissos;
    }
}
