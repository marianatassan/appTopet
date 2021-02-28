package com.example.apptopet.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.activity.ViewPostActivity;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;
import com.example.apptopet.activity.PostsActivity;
import com.example.apptopet.model.PostagemItem;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter {

    Context context;
    List<PostagemItem> postagemItems;

    public PostsAdapter(Context context, List<PostagemItem> postagemItems) {
        this.context = context;
        this.postagemItems = postagemItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_lista_social, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final PostagemItem postagemItem = this.postagemItems.get(position);

        ImageView imvPhoto = holder.itemView.findViewById(R.id.imvSocial);
        imvPhoto.setImageBitmap(postagemItem.getImg());

        TextView tvTitle = holder.itemView.findViewById(R.id.tvNomeA);
        tvTitle.setText(postagemItem.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ViewPostActivity.class);
                i.putExtra("id_publicacao", postagemItem.getId_postagem());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() { return this.postagemItems.size(); }
}
