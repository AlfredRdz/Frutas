package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Listado extends AppCompatActivity implements SearchView.OnQueryTextListener {

    FloatingActionButton btn_agregar;
    RecyclerView recyclerView;
    ImageView imageView_vaciop;

    FrutaAdapter customAdapter;
    List<Fruta> lista ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        btn_agregar = findViewById(R.id.btn_agregar);
        recyclerView = findViewById(R.id.recyclerView);
        imageView_vaciop = findViewById(R.id.imageView_vaciop);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        FrutaCon frutaCon = new FrutaCon(getApplicationContext());
        frutaCon.open();
        lista = frutaCon.obtenerFrutas();

        if (lista.size() > 0){
            customAdapter = new FrutaAdapter(lista, getApplicationContext());
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
        lista = frutaCon.obtenerFrutas();

        if (lista.size() > 0){
            FrutaAdapter customAdapter = new FrutaAdapter(lista, getApplicationContext());
            recyclerView.setAdapter(customAdapter);
            imageView_vaciop.setVisibility(View.GONE);
        }else{
            imageView_vaciop.setVisibility(View.VISIBLE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.barra_busqueda, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //customAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Fruta> list = new ArrayList<>();
        for (Fruta fruta: lista){
            String nombre = fruta.getNombre().toLowerCase();
            if (nombre.contains(newText)){
                list.add(fruta);
            }
        }

        customAdapter.setFilter(list);
        return true;
    }

}