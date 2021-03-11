package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.activity.ListaAnimaisActivity;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;

import java.util.List;

public class ListaAnimaisAdapter extends RecyclerView.Adapter {

    ListaAnimaisActivity listaAnimaisActivity;
    List<MyItem> animais;

    public ListaAnimaisAdapter(ListaAnimaisActivity listaAnimaisActivity, List<MyItem> animais) {
        this.listaAnimaisActivity = listaAnimaisActivity;
        this.animais = animais;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(listaAnimaisActivity);
        View v = inflater.inflate(R.layout.item_lista_animais, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        View v = holder.itemView;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaAnimaisActivity.startPerfilAnimal(animais.get(position));
            }
        });

        MyItem myItem = animais.get(position);

        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageURI(myItem.fotoPerfil);

        TextView tvNome = v.findViewById(R.id.tvNome);
        tvNome.setText(myItem.nomeAnimal);
    }

    @Override
    public int getItemCount() {
        return animais.size();
    }
}

