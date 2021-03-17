package com.example.apptopet.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apptopet.activity.AddMedicationActivity;
import com.example.apptopet.R;
import com.example.apptopet.adapter.MedicationsAdapter;
import com.example.apptopet.adapter.VaccinesAdapter;
import com.example.apptopet.model.Medication;
import com.example.apptopet.model.MyDAO;
import com.example.apptopet.model.MyDB;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.RemedioViewModel;
import com.example.apptopet.model.Vaccine;
import com.example.apptopet.model.VacinaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicationsFragment extends Fragment {
    String nomeAnimal;

    static int NEW_ITEM_REQUEST = 1;

    public MedicationsFragment() {
        // Required empty public constructor
    }

    public static MedicationsFragment newInstance(String nomeAnimal) {
        MedicationsFragment fragment = new MedicationsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nomeAnimal", nomeAnimal);
        fragment.setArguments(bundle);
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
                Intent i = new Intent(getActivity(), AddMedicationActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        nomeAnimal = bundle.getString("nomeAnimal");

        RemedioViewModel remedioViewModel = new ViewModelProvider(this, new RemedioViewModel.RemedioViewModelFactory(getActivity().getApplication(), nomeAnimal)).get(RemedioViewModel.class);
        final LiveData<List<Medication>> medications = remedioViewModel.getItems();

        final RecyclerView rvMedications = getView().findViewById(R.id.rvMedications);

        medications.observe(getViewLifecycleOwner(), new Observer<List<Medication>>() {
            @Override
            public void onChanged(List<Medication> medications) {
                MedicationsAdapter medicationsAdapter = new MedicationsAdapter(MedicationsFragment.this, medications);
                rvMedications.setAdapter(medicationsAdapter);
            }
        });

        rvMedications.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMedications.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvMedications.getContext(), DividerItemDecoration.VERTICAL);
        rvMedications.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String nome = data.getStringExtra("nome");
                String dataMedicacao = data.getStringExtra("dataMedicacao");
                String dataProximaDose = data.getStringExtra("dataProximaDose");
                String peso = data.getStringExtra("peso");
                Float pesoFloat = Float.parseFloat(peso);

                final Medication newMedication = new Medication();
                newMedication.nome = nome;
                newMedication.peso = pesoFloat;
                newMedication.nomeAnimalMedication = nomeAnimal;
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataMedicacao);
                    newMedication.dtRemedio = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataProximaDose);
                    newMedication.dtRemedio2 = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        MyDAO myDAO = MyDB.getInstance(getActivity().getApplication()).myDAO();
                        myDAO.insertMedication(newMedication);
                    }
                });
            }
        }
    }
}