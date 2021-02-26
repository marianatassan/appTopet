package com.example.apptopet.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptopet.activity.AddVaccineActivity;
import com.example.apptopet.R;
import com.example.apptopet.adapter.MyAdapter3;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.VacinaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class VaccinesFragment extends Fragment {
    MyAdapter3 myAdapter3;


    public VaccinesFragment() {
        // Required empty public constructor
    }

    public static VaccinesFragment newInstance() {
        VaccinesFragment fragment = new VaccinesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vaccines, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabAddVaccines = getView().findViewById(R.id.fabAddVaccines);
        fabAddVaccines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddVaccineActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        VacinaViewModel vm = new ViewModelProvider(getActivity()).get(VacinaViewModel.class);
        List<MyItem> vacinas = vm.getItems();

        myAdapter3 = new MyAdapter3(this, vacinas);

        RecyclerView rvVaccines = getView().findViewById(R.id.rvVaccines);
        rvVaccines.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvVaccines.setLayoutManager(layoutManager);

        rvVaccines.setAdapter(myAdapter3);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvVaccines.getContext(), DividerItemDecoration.VERTICAL);
        rvVaccines.addItemDecoration(dividerItemDecoration);

    }

}