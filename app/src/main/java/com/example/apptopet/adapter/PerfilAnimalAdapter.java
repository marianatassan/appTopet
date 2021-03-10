package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.model.MyItem;
import com.example.apptopet.activity.PerfilAnimalActivity;
import com.example.apptopet.R;

import java.util.List;

public class PerfilAnimalAdapter extends RecyclerView.Adapter {
    PerfilAnimalActivity perfilAnimalActivity;
    List<MyItem> compromissos;

    public PerfilAnimalAdapter(PerfilAnimalActivity perfilAnimalActivity, List<MyItem> compromissos) {
        this.perfilAnimalActivity = perfilAnimalActivity;
        this.compromissos = compromissos;
    }


    @NonNull
    @Override


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(perfilAnimalActivity);
        View v2 = inflater.inflate(R.layout.item_lista_compromissos, parent, false);
        return new RecyclerView.ViewHolder(v2) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = compromissos.get(position);
        View v2 = holder.itemView;

        TextView tvCompromisso = v2.findViewById(R.id.tvCompromisso);
        tvCompromisso.setText(myItem.compromisso);

        TextView tvData = v2.findViewById(R.id.tvData);
        tvData.setText(myItem.data);

    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

}