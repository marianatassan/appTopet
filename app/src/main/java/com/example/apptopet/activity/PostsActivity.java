package com.example.apptopet.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.example.apptopet.model.PostagemItem;
import com.example.apptopet.model.PostsViewModel;
import com.example.apptopet.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostsActivity extends AppCompatActivity {

    static int ADD_PUBLICACAO_ACTIVITY_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        FloatingActionButton fabAddNewItem = findViewById(R.id.floatingActionButton);
        fabAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostsActivity.this, AdicionarPostagemActivity.class);
                startActivityForResult(i, ADD_PUBLICACAO_ACTIVITY_RESULT);
            }
        });

        final RecyclerView rvFotosSocial = findViewById(R.id.rvFotosSocial);
        rvFotosSocial.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFotosSocial.setLayoutManager(layoutManager);

        float w = getResources().getDimension(R.dimen.itemWidth);
        int numberOfColumns = Utils.calculateNumberOfColumns(PostsActivity.this, w);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PostsActivity.this, numberOfColumns);
        rvFotosSocial.setLayoutManager(gridLayoutManager);

        PostsViewModel postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
        LiveData<List<PostagemItem>> postagemItems = postsViewModel.getPostagemItems();
        postagemItems.observe(this, new Observer<List<PostagemItem>>() {
            @Override
            public void onChanged(List<PostagemItem> postagemItems) {
                PostsAdapter postsAdapter = new PostsAdapter(PostsActivity.this, postagemItems);
                rvFotosSocial.setAdapter(postsAdapter);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PUBLICACAO_ACTIVITY_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                PostsViewModel postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
                postsViewModel.refreshPostagens();
            }
        }
    }

}