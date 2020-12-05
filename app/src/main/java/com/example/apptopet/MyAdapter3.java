package com.example.apptopet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter {
    VaccinesFragment vaccinesFragment;
    List<MyItem> vacinas;



    public MyAdapter3(VaccinesFragment vaccinesFragment, List<MyItem> vacinas) {
        this.vaccinesFragment = vaccinesFragment;
        this.vacinas = vacinas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(vaccinesFragment);
        View v3 = inflater.inflate(R.layout.item_lista_vacinas, parent, false);
        return new RecyclerView.ViewHolder(v3) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = vacinas.get(position);
        View v3 = holder.itemView;

        TextView tvVacinaNome = v3.findViewById(R.id.tvVacinaNome);
        tvVacinaNome.setText(myItem.nomeVacina);

        TextView tvVacinaDt = v3.findViewById(R.id.tvVacinaDt);
        tvVacinaDt.setText(myItem.dtVacina);

        TextView tvVacinaDt2 = v3.findViewById(R.id.tvVacinaDt2);
        tvVacinaDt2.setText(myItem.dtRevacina);

        TextView tvVacinaPeso = v3.findViewById(R.id.tvVacinaPeso);
        tvVacinaPeso.setText(myItem.pesoVacina);

    }

    @Override
    public int getItemCount() { return vacinas.size();
    }
}
