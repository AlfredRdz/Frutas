package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Listado extends AppCompatActivity {

    FloatingActionButton btn_agregar;
    RecyclerView recyclerView;
    ImageView imageView_vaciop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        btn_agregar = findViewById(R.id.btn_agregar);
        recyclerView = findViewById(R.id.recyclerView);
        imageView_vaciop = findViewById(R.id.imageView_vaciop);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        FrutaCon frutaCon = new FrutaCon(getApplicationContext());
        frutaCon.open();
        List<Fruta> lista = frutaCon.obtenerFrutas();

        if (lista.size() > 0){
            FrutaAdapter customAdapter = new FrutaAdapter(lista, getApplicationContext());
            recyclerView.setAdapter(customAdapter);
            imageView_vaciop.setVisibility(View.GONE);
        }else{
            imageView_vaciop.setVisibility(View.VISIBLE);
        }

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listado.this, AgregarFruta.class);
                startActivity(intent);
            }
        });
    }

    public void onResume() {
        super.onResume();


        FrutaCon frutaCon = new FrutaCon(getApplicationContext());
        frutaCon.open();
        List<Fruta> lista = frutaCon.obtenerFrutas();

        if (lista.size() > 0){
            FrutaAdapter customAdapter = new FrutaAdapter(lista, getApplicationContext());
            recyclerView.setAdapter(customAdapter);
            imageView_vaciop.setVisibility(View.GONE);
        }else{
            imageView_vaciop.setVisibility(View.VISIBLE);
        }
    }
}