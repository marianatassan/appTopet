package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter {
    PerfilAnimal perfilAnimal;
    List<MyItem2> compromissos;

    public MyAdapter2(PerfilAnimal perfilAnimal, List<MyItem2> compromissos) {
        this.perfilAnimal = perfilAnimal;
        this.compromissos = compromissos;
    }

    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(perfilAnimal);
        View v2 = inflater.inflate(R.layout.item_lista_compromissos, parent, false);
        return new RecyclerView.ViewHolder(v2) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem2 myItem2 = compromissos.get(position);
        View v2 = holder.itemView;

        TextView tvCompromisso = v2.findViewById(R.id.tvCompromisso);
        tvCompromisso.setText(myItem2.compromisso);

        TextView tvData = v2.findViewById(R.id.tvData);
        tvData.setText(myItem2.data);

    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

}
