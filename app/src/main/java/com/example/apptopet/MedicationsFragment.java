package com.example.apptopet;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MedicationsFragment extends Fragment {
    MyAdapter4 myAdapter4;

    public MedicationsFragment() {
        // Required empty public constructor
    }

    public static MedicationsFragment newInstance() {
        MedicationsFragment fragment = new MedicationsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabAddMedication = getView().findViewById(R.id.fabAddMedication);
        fabAddMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddMedication.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RemedioViewModel vm = new ViewModelProvider(getActivity()).get(RemedioViewModel.class);
        List<MyItem> remedios = vm.getItems();

        myAdapter4 = new MyAdapter4(this, remedios);

        RecyclerView rvMedications = getView().findViewById(R.id.rvMedications);
        rvMedications.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMedications.setLayoutManager(layoutManager);

        rvMedications.setAdapter(myAdapter4);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMedications.getContext(), DividerItemDecoration.VERTICAL);
        rvMedications.addItemDecoration(dividerItemDecoration);
    }
}