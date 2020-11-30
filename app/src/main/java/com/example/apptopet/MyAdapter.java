package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    AnimaisLista animaisLista;
    PerfilAnimal perfilAnimal;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyItem myItem = animais.get(position);

        View v = holder.itemView;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animaisLista.startPerfilAnimal(animais.get(position));
            }
        });

        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageResource(myItem.foto);

        TextView tvNome = v.findViewById(R.id.tvNome);
        tvNome.setText(myItem.nome);


    }

    @Override
    public int getItemCount() {
        return animais.size();
    }
}

