package com.example.apptopet.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListaCompromissosViewModel extends ViewModel {
    List<MyItem> compromissos2 = new ArrayList<>();

    public ListaCompromissosViewModel() {
        MyItem compromisso1 = new MyItem();
        compromisso1.nomeCompromisso = "Veterinário";
        compromisso1.nomePet = "Pet: Dudu";
        compromisso1.dtCompromisso = "Data: 19/12/2020";
        compromisso1.dias = "Falta 2 dias";
        MyItem compromisso2 = new MyItem();
        compromisso2.nomeCompromisso = "Escovação";
        compromisso2.nomePet = "Pet: Mingau";
        compromisso2.dtCompromisso = "Data: 19/12/2020";
        compromisso2.dias = "Falta 5 dias";
        MyItem compromisso3 = new MyItem();
        compromisso3.nomeCompromisso = "Cortar asas";
        compromisso3.nomePet = "Pet: Tico";
        compromisso3.dtCompromisso = "Data: 19/12/2020";
        compromisso3.dias = "Falta 12 dias";
        MyItem compromisso4 = new MyItem();
        compromisso4.nomeCompromisso = "Cortar unhas";
        compromisso4.nomePet = "Pet: Thor";
        compromisso4.dtCompromisso = "Data: 19/12/2020";
        compromisso4.dias = "Falta 12 dias";
        MyItem compromisso5 = new MyItem();
        compromisso5.nomeCompromisso = "Cortar unhas";
        compromisso5.nomePet = "Pet: Dudu";
        compromisso5.dtCompromisso = "Data: 19/12/2020";
        compromisso5.dias = "Falta 12 dias";
        MyItem compromisso6 = new MyItem();
        compromisso6.nomeCompromisso = "Veterinário";
        compromisso6.nomePet = "Pet: Toby";
        compromisso6.dtCompromisso = "Data: 19/12/2020";
        compromisso6.dias = "Falta 3 semanas";
        MyItem compromisso7 = new MyItem();
        compromisso7.nomeCompromisso = "Banho";
        compromisso7.nomePet = "Pet: Mingau";
        compromisso7.dtCompromisso = "Data: 19/12/2020";
        compromisso7.dias = "Falta 1 mês";

        compromissos2.add(compromisso1);
        compromissos2.add(compromisso2);
        compromissos2.add(compromisso3);
        compromissos2.add(compromisso4);
        compromissos2.add(compromisso5);
        compromissos2.add(compromisso6);
        compromissos2.add(compromisso7);
    }

    public List<MyItem> getItems() {
        return compromissos2;
    }
}
