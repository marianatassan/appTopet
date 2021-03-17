package com.example.apptopet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptopet.model.Compromisso;
import com.example.apptopet.model.MyItem;
import com.example.apptopet.R;
import com.example.apptopet.fragments.VaccinesFragment;
import com.example.apptopet.model.Vaccine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VaccinesAdapter extends RecyclerView.Adapter {
    VaccinesFragment vaccinesFragment;
    List<Vaccine> vaccines;



    public VaccinesAdapter(VaccinesFragment vaccinesFragment, List<Vaccine> vaccines) {
        this.vaccinesFragment = vaccinesFragment;
        this.vaccines = vaccines;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(vaccinesFragment.getActivity());
        View v3 = inflater.inflate(R.layout.item_lista_vacinas, parent, false);
        return new RecyclerView.ViewHolder(v3) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Vaccine vaccine = vaccines.get(position);
        View v = holder.itemView;

        Date date = new Date(vaccine.dtVacina);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);

        Date date2 = new Date(vaccine.dtRevacina);
        SimpleDateFormat df22 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText2 = df22.format(date2);

        TextView tvVacinaNome = v.findViewById(R.id.tvVacinaNome);
        tvVacinaNome.setText(vaccine.nome);

        TextView tvVacinaDt = v.findViewById(R.id.tvVacinaDt);
        tvVacinaDt.setText(dateText);

        TextView tvVacinaDt2 = v.findViewById(R.id.tvVacinaDt2);
        tvVacinaDt2.setText(dateText2);

        String peso = String.valueOf(vaccine.peso);

        TextView tvVacinaPeso = v.findViewById(R.id.tvVacinaPeso);
        tvVacinaPeso.setText(peso);

    }

    @Override
    public int getItemCount() { return vaccines.size();
    }
}
