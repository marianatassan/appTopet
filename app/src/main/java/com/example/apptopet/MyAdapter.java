package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    AnimaisLista animaisLista;
    List<MyItem> animais;

    public MyAdapter(AnimaisLista animaisLista, List<MyItem> animais) {
        this.animaisLista = animaisLista;
        this.animais = animais;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(animaisLista);
        View v = inflater.inflate(R.layout.item_lista_animais, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = animais.get(position);

        View v = holder.itemView;

        TextInputEditText tvNome = v.findViewById(R.id.etNome);
        tvNome.setText(myItem.nome);


    }

    @Override
    public int getItemCount() {
        return animais.size();
    }
}

