package com.example.apptopet.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.activity.ListaAnimaisActivity;
import com.example.apptopet.model.Animal;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;
import com.example.apptopet.util.Utils;

import java.util.List;

public class ListaAnimaisAdapter extends RecyclerView.Adapter {

    ListaAnimaisActivity listaAnimaisActivity;
    List<Animal> animais;

    public ListaAnimaisAdapter(ListaAnimaisActivity listaAnimaisActivity, List<Animal> animais) {
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
        final Animal animal = animais.get(position);
        View v = holder.itemView;
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaAnimaisActivity.startPerfilAnimal(animal);
            }
        });

        Bitmap bmp = Utils.getBitmap(animal.foto);
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageBitmap(bmp);

        TextView tvNome = v.findViewById(R.id.tvNome);
        tvNome.setText(animal.nomeAnimal);
    }

    @Override
    public int getItemCount() {
        return animais.size();
    }
}

