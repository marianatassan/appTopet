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

import com.example.apptopet.activity.AddVaccineActivity;
import com.example.apptopet.R;
import com.example.apptopet.activity.PerfilAnimalActivity;
import com.example.apptopet.adapter.PerfilAnimalAdapter;
import com.example.apptopet.adapter.VaccinesAdapter;
import com.example.apptopet.model.Compromisso;
import com.example.apptopet.model.MyDAO;
import com.example.apptopet.model.MyDB;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.model.PerfilAnimalViewModel;
import com.example.apptopet.model.Vaccine;
import com.example.apptopet.model.VacinaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VaccinesFragment extends Fragment {
    String nomeAnimal;

    static int NEW_ITEM_REQUEST = 1;

    public VaccinesFragment() {
        // Required empty public constructor
    }

    public static VaccinesFragment newInstance(String nomeAnimal) {
        VaccinesFragment fragment = new VaccinesFragment();
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
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        nomeAnimal = bundle.getString("nomeAnimal");

        VacinaViewModel vacinaViewModel = new ViewModelProvider(this, new VacinaViewModel.VacinaViewModelFactory(getActivity().getApplication(), nomeAnimal)).get(VacinaViewModel.class);
        final LiveData<List<Vaccine>> vaccines = vacinaViewModel.getItems();

        final RecyclerView rvVaccines = getView().findViewById(R.id.rvVaccines);

        vaccines.observe(getViewLifecycleOwner(), new Observer<List<Vaccine>>() {
            @Override
            public void onChanged(List<Vaccine> vaccines) {
                VaccinesAdapter vaccinesAdapter = new VaccinesAdapter(VaccinesFragment.this, vaccines);
                rvVaccines.setAdapter(vaccinesAdapter);
            }
        });

        rvVaccines.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvVaccines.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvVaccines.getContext(), DividerItemDecoration.VERTICAL);
        rvVaccines.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle bundle = getArguments();
                String nomeAnimal = bundle.getString("nomeAnimal");
                String nome = data.getStringExtra("nome");
                String dataVacina = data.getStringExtra("dataVacina");
                String dataRevacina = data.getStringExtra("dataRevacina");
                String peso = data.getStringExtra("peso");
                Float pesoFloat = Float.parseFloat(peso);

                final Vaccine newVaccine = new Vaccine();
                newVaccine.nome = nome;
                newVaccine.peso = pesoFloat;
                newVaccine.nomeAnimalVaccine = nomeAnimal;
                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataVacina);
                    newVaccine.dtVacina = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataRevacina);
                    newVaccine.dtRevacina = date.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        MyDAO myDAO = MyDB.getInstance(getActivity().getApplication()).myDAO();
                        myDAO.insertVaccine(newVaccine);
                    }
                });
            }
        }
    }
}