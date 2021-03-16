package com.example.apptopet.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.model.Animal;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.activity.PerfilAnimalActivity;
import com.example.apptopet.R;
import com.example.apptopet.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PerfilAnimalAdapter extends RecyclerView.Adapter {
    PerfilAnimalActivity perfilAnimalActivity;
    List<Compromisso> compromissos;

    public PerfilAnimalAdapter(PerfilAnimalActivity perfilAnimalActivity, List<Compromisso> compromissos) {
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

        final Compromisso compromisso = compromissos.get(position);
        View v = holder.itemView;

        TextView tvDescricao = v.findViewById(R.id.tvCompromisso);
        tvDescricao.setText(compromisso.descricao);

        Date date = new Date(compromisso.data);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);

        TextView tvData = v.findViewById(R.id.tvData);
        tvData.setText(dateText);

    }

    @Override
    public int getItemCount() {
        return compromissos.size();
    }

}
