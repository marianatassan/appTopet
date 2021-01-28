package com.example.apptopet;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter {

    Social social;
    List<MyItem> photos;

    public SocialAdapter(Social social, List<MyItem> photos) {
        this.social = social;
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(social);
        View v = inflater.inflate(R.layout.item_lista_social, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = photos.get(position);

        View v = holder.itemView;

        ImageView imvPhoto = v.findViewById(R.id.imvSocial);
        imvPhoto.setImageURI(myItem.fotoSocial);

        TextView tvTitle = v.findViewById(R.id.tvNomeA);
        tvTitle.setText(myItem.titulo);
    }

    @Override
    public int getItemCount() { return photos.size(); }
}
