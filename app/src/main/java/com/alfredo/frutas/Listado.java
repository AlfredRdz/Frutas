package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.conexion.Fruta;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Listado extends AppCompatActivity  {

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


        AppDataBase appDataBase = AppDataBase.getInstance(this.getApplicationContext());
        List<Fruta> lista = appDataBase.frutaDao().getAllFrutas();

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

        AppDataBase appDataBase = AppDataBase.getInstance(this.getApplicationContext());
        List<Fruta> lista = appDataBase.frutaDao().getAllFrutas();

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
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText.toString());
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cerrar_sesion:
                SharedPreferences prefs = this.getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", "");     //RESET TO DEFAULT VALUE
                editor.putString("contraseña", "");     //RESET TO DEFAULT VALUE
                editor.commit();
                this.finish();
                startActivity(new Intent(this, MainActivity.class));
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}