package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.apptopet.R;
import com.example.apptopet.adapter.PostsAdapter;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.PostsViewModel;
import com.example.apptopet.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostsActivity extends AppCompatActivity {
    PostsAdapter postsAdapter;
    static int PHOTO_PICKER_REQUEST = 1;
    static int NEW_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        FloatingActionButton fabAddNewItem = findViewById(R.id.floatingActionButton);
        fabAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostsActivity.this, AdicionarPostagemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        PostsViewModel vm = new ViewModelProvider(this).get(PostsViewModel.class);
        List<MyItem> photos = vm.getItems();

        postsAdapter = new PostsAdapter(this, photos);

        RecyclerView rvFotosSocial = findViewById(R.id.rvFotosSocial);
        rvFotosSocial.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFotosSocial.setLayoutManager(layoutManager);

        rvFotosSocial.setAdapter(postsAdapter);

        float w = getResources().getDimension(R.dimen.itemWidth);
        int numberOfColumns = Utils.calculateNumberOfColumns(PostsActivity.this, w);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PostsActivity.this, numberOfColumns);
        rvFotosSocial.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedPhotoLocation = data.getData();
                String title = data.getStringExtra("title");

                MyItem newItem = new MyItem();
                newItem.fotoSocial = selectedPhotoLocation;
                newItem.titulo = title;

                PostsViewModel vm = new ViewModelProvider(this).get(PostsViewModel.class);
                List<MyItem> photos = vm.getItems();

                photos.add(newItem);

                postsAdapter.notifyItemInserted(photos.size()-1);
            }
        }
    }

    public void startZoomSocial(MyItem photos) {
        Intent i = new Intent(PostsActivity.this, ViewPostActivity.class);
        startActivity(i);
    }

}