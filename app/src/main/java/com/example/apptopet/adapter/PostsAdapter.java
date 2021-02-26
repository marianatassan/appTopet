package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;
import com.example.apptopet.activity.PostsActivity;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter {

    PostsActivity postsActivity;
    List<MyItem> photos;

    public PostsAdapter(PostsActivity postsActivity, List<MyItem> photos) {
        this.postsActivity = postsActivity;
        this.photos = photos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(postsActivity);
        View v = inflater.inflate(R.layout.item_lista_social, parent, false);
        return new RecyclerView.ViewHolder(v) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyItem myItem = photos.get(position);

        View v = holder.itemView;
        View vFix = holder.itemView;

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsActivity.startZoomSocial(photos.get(position));
            }
        });

        ImageView imvPhotoFix = vFix.findViewById(R.id.imvSocial);
        imvPhotoFix.setImageResource(myItem.postagem);

        ImageView imvPhoto = v.findViewById(R.id.imvSocial);
        imvPhoto.setImageURI(myItem.fotoSocial);

        TextView tvTitle = v.findViewById(R.id.tvNomeA);
        tvTitle.setText(myItem.titulo);

        TextView tvTitleFix = vFix.findViewById(R.id.tvNomeA);
        tvTitleFix.setText(myItem.tituloFix);
    }

    @Override
    public int getItemCount() { return photos.size(); }
}
