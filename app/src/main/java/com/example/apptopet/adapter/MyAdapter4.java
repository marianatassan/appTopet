package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.fragments.MedicationsFragment;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;

import java.util.List;

public class MyAdapter4 extends RecyclerView.Adapter {
    MedicationsFragment medicationsFragment;
    List<MyItem> remedios;

    public MyAdapter4(MedicationsFragment medicationsFragment, List<MyItem> remedios) {
        this.medicationsFragment = medicationsFragment;
        this.remedios = remedios;
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
        MyItem myItem = remedios.get(position);
        View v4 = holder.itemView;

        TextView tvRemedioNome = v4.findViewById(R.id.tvRemedioNome);
        tvRemedioNome.setText(myItem.nomeRemedio);

        TextView tvRemedioDt = v4.findViewById(R.id.tvRemedioDt);
        tvRemedioDt.setText(myItem.dtRemedio);

        TextView tvRemedioDt2 = v4.findViewById(R.id.tvRemedioDt2);
        tvRemedioDt2.setText(myItem.dtRemedio2);

        TextView tvRemedioPeso = v4.findViewById(R.id.tvRemedioPeso);
        tvRemedioPeso.setText(myItem.pesoRemedio);

    }

    @Override
    public int getItemCount() { return remedios.size();}
    }
