package com.example.apptopet.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.activity.ListaCompromissosActivity;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaCompromissosAdapter extends RecyclerView.Adapter {

    ListaCompromissosActivity listaCompromissosActivity;
    List<Compromisso> compromissos;

    public ListaCompromissosAdapter(ListaCompromissosActivity listaCompromissosActivity, List<Compromisso> compromissos) {
        this.listaCompromissosActivity = listaCompromissosActivity;
        this.compromissos = compromissos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(listaCompromissosActivity);
        View v = inflater.inflate(R.layout.item_lista_compromissos2, parent, false);
        return new RecyclerView.ViewHolder(v) {};
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Compromisso compromisso = compromissos.get(position);
        View v = holder.itemView;

        TextView tvTituloCompromisso = v.findViewById(R.id.tvTituloCompromisso);
        tvTituloCompromisso.setText(compromisso.descricao);

        TextView tvNomePet = v.findViewById(R.id.tvNomePet);
        tvNomePet.setText(compromisso.nomeAnimal);

        Date date = new Date(compromisso.data);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);

        TextView tvDataCompromisso = v.findViewById(R.id.tvDataCompromisso);
        tvDataCompromisso.setText(dateText);

        try {
            Date data2 = df2.parse(dateText);
            Date dataAtual = new Date();
            String dataAtualText = df2.format(dataAtual);
            Date dataAtual2 = df2.parse(dataAtualText);
            long dias = (data2.getTime() - dataAtual2.getTime()) / (1000*60*60*24);
            TextView tvDias = v.findViewById(R.id.tvDias);
            tvDias.setText("Faltam " + String.valueOf(dias)+ " dias");

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() { return compromissos.size(); }

}
