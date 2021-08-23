package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chips extends AppCompatActivity implements Serializable {
    Chip chipA, chipC, chipD, chipE, chipK, chipB1;
    ChipGroup chipGroup;
    Button btn_seleccionarChips;
    List<String> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);

        chipA = findViewById(R.id.chipA);
        chipC = findViewById(R.id.chipC);
        chipD = findViewById(R.id.chipD);
        chipE = findViewById(R.id.chipE);
        chipK = findViewById(R.id.chipK);
        chipB1 = findViewById(R.id.chipB1);
        chipGroup = findViewById(R.id.chipGroup);
        btn_seleccionarChips = findViewById(R.id.btn_seleccionarChips);

        btn_seleccionarChips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i< chipGroup.getChildCount(); i++){
                    Chip chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isChecked()){
                        if (i < chipGroup.getChildCount() - 1 ){
                            builder.append(chip.getText()).append(",");
                            lista.add(chip.getText().toString());
                        } else {
                            builder.append(chip.getText());
                            lista.add(chip.getText().toString());
                        }
                    }
                    //chipGroup.addView(chip);
                }
                //Toast.makeText(Chips.this, builder.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Chips.this, AgregarFruta.class);
                intent.putExtra("lista", (Serializable) lista);
                startActivity(intent);

                //Toast.makeText(Chips.this, lista.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        chipA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    Toast.makeText(Chips.this, "si " + chipA.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(Chips.this, "si " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(Chips.this, "si " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipE.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(Chips.this, "si " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(Chips.this, "si " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chipB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(Chips.this, "si " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}