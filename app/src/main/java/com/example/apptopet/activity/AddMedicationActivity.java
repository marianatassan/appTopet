package com.example.apptopet.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptopet.R;

public class AddMedicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_medication);

        Button btnAddMedication = findViewById(R.id.btnAddMedication);
        btnAddMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etAddNameMedication = findViewById(R.id.etAddNameMedication);
                String nome = etAddNameMedication.getText().toString();

                EditText etDataMedicacao = findViewById(R.id.etDataMedicacao);
                String dataMedicacao = etDataMedicacao.getText().toString();

                EditText etDataProximaDose = findViewById(R.id.etDataProximaDose);
                String dataProximaDose = etDataProximaDose.getText().toString();

                EditText etAddPesoVacina = findViewById(R.id.etAddPesoVacina);
                String peso = etAddPesoVacina.getText().toString();

                if (nome.isEmpty() || dataMedicacao.isEmpty() || dataProximaDose.isEmpty() || peso.isEmpty()) {
                    Toast.makeText(AddMedicationActivity.this, "Você possui campo(s) não preenchido(s).", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("nome", nome);
                i.putExtra("dataMedicacao", dataMedicacao);
                i.putExtra("dataProximaDose", dataProximaDose);
                i.putExtra("peso", peso);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}
