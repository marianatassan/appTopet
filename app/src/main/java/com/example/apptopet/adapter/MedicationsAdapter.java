package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.fragments.MedicationsFragment;
import com.example.apptopet.model.Medication;
import com.example.apptopet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicationsAdapter extends RecyclerView.Adapter {
    MedicationsFragment medicationsFragment;
    List<Medication> medications;

    public MedicationsAdapter(MedicationsFragment medicationsFragment, List<Medication> medications) {
        this.medicationsFragment = medicationsFragment;
        this.medications = medications;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater2 = LayoutInflater.from(medicationsFragment.getActivity());
        View v4 = inflater2.inflate(R.layout.item_lista_remedios, parent, false);
        return new RecyclerView.ViewHolder(v4) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Medication medication = medications.get(position);
        View v = holder.itemView;

        Date date = new Date(medication.dtRemedio);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);

        Date date2 = new Date(medication.dtRemedio2);
        SimpleDateFormat df22 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText2 = df22.format(date2);

        TextView tvRemedioNome = v.findViewById(R.id.tvRemedioNome);
        tvRemedioNome.setText(medication.nome);

        TextView tvRemedioDt = v.findViewById(R.id.tvRemedioDt);
        tvRemedioDt.setText(dateText);

        TextView tvRemedioDt2 = v.findViewById(R.id.tvRemedioDt2);
        tvRemedioDt2.setText(dateText2);

        String peso = String.valueOf(medication.peso);

        TextView tvRemedioPeso = v.findViewById(R.id.tvRemedioPeso);
        tvRemedioPeso.setText(peso);

    }

    @Override
    public int getItemCount() { return medications.size();}
    }
