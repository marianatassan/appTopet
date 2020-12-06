package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CompromissosAdapter extends RecyclerView.Adapter {

    Compromissos compromissos;
    List<MyItem> compromissos2;

    public CompromissosAdapter(Compromissos compromissos, List<MyItem> compromissos2) {
        this.compromissos = compromissos;
        this.compromissos2 = compromissos2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(compromissos);
        View v = inflater.inflate(R.layout.item_lista_compromissos2, parent, false);
        return new RecyclerView.ViewHolder(v) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = compromissos2.get(position);

        View v = holder.itemView;

        TextView tvTituloCompromisso = v.findViewById(R.id.tvTituloCompromisso);
        tvTituloCompromisso.setText(myItem.nomeCompromisso);

        TextView tvNomePet = v.findViewById(R.id.tvNomePet);
        tvNomePet.setText(myItem.nomePet);

        TextView tvDataCompromisso = v.findViewById(R.id.tvDataCompromisso);
        tvDataCompromisso.setText(myItem.dtCompromisso);

        TextView tvDias = v.findViewById(R.id.tvDias);
        tvDias.setText(myItem.dias);
    }

    @Override
    public int getItemCount() { return compromissos2.size(); }
}
