package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter4 extends RecyclerView.Adapter {
    Saude saude2;
    List<MyItem> remedios;

    public MyAdapter4(Saude saude2, List<MyItem> remedios) {
        this.saude2 = saude2;
        this.remedios = remedios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater2 = LayoutInflater.from(saude2);
        View v4 = inflater2.inflate(R.layout.item_lista_remedios, parent, false);
        return new RecyclerView.ViewHolder(v4) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = remedios.get(position);
        View v4 = holder.itemView;

        TextView tvRemedioNome = v4.findViewById(R.id.tvRemedioNome);
        tvRemedioNome.setText(myItem.nomeRemedio);

        TextView tvRemedioDt = v4.findViewById(R.id.tvRemedioDt);
        tvRemedioDt.setText(myItem.dtRemedio);

        TextView tvRemedioDt2 = v4.findViewById(R.id.tvRemedioDt2);
        tvRemedioDt2.setText(myItem.dtRemedio2);

        TextView tvRemedioPeso = v4.findViewById(R.id.tvRemedioPeso);
        tvRemedioPeso.setText(myItem.pesoRemedio);

    }

    @Override
    public int getItemCount() { return remedios.size();}
    }
