package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;

public class ActualizarFruta extends AppCompatActivity {

    EditText edt_nombreAC, edt_colorAC, edt_cantidadAC, edt_imagenAC;
    Button btn_agregarFruta, btn_eliminar;
    String id, nombre, color, cantidad, imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_fruta);

        edt_nombreAC = findViewById(R.id.edt_nombreAC);
        edt_colorAC = findViewById(R.id.edt_colorAC);
        edt_cantidadAC = findViewById(R.id.edt_cantidadAC);
        edt_imagenAC = findViewById(R.id.edt_imagenAC);
        btn_agregarFruta = findViewById(R.id.btn_agregarFruta);
        btn_eliminar = findViewById(R.id.btn_eliminar);

        getIntentData();
        btn_agregarFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_nombreAC.getText().length() > 0 && edt_colorAC.getText().length() > 0){
                    FrutaCon frutaCon = new FrutaCon(getApplicationContext());
                    frutaCon.open();

                    String nombre = edt_nombreAC.getText().toString();
                    String color = edt_colorAC.getText().toString();
                    Integer cantidad = Integer.parseInt(edt_cantidadAC.getText().toString());
                    String imagen = edt_imagenAC.getText().toString();

                    Fruta fruta = new Fruta(Integer.valueOf(id), nombre, color, cantidad, imagen);
                    frutaCon.actualizarFruta(fruta);

                    Toast.makeText(ActualizarFruta.this, "Se ha actualizado la fruta", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(ActualizarFruta.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrutaCon frutaCon = new FrutaCon(getBaseContext());
                frutaCon.open();

                frutaCon.eliminarFruta(Integer.valueOf(id));
                Toast.makeText(ActualizarFruta.this, "Se ha eliminado la fruta", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    void getIntentData(){
        if (getIntent().hasExtra("nombre") && getIntent().hasExtra("color") && getIntent().hasExtra("cantidad")){
            id = getIntent().getStringExtra("id_fruta");
            nombre = getIntent().getStringExtra("nombre");
            color = getIntent().getStringExtra("color");
            cantidad = getIntent().getStringExtra("cantidad");
            imagen = getIntent().getStringExtra("imagen");

            edt_nombreAC.setText(nombre);
            edt_colorAC.setText(color);
            edt_cantidadAC.setText(cantidad);
            edt_imagenAC.setText(imagen);
        }
    }
}
