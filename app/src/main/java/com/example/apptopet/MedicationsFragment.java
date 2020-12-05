package com.example.apptopet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medications, container, false);
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