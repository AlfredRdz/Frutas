package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;

public class AgregarFruta extends AppCompatActivity {
    Button btn_agregarFruta;
    EditText edt_nombre, edt_color, edt_cantidad, edt_imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_color = findViewById(R.id.edt_color);
        edt_cantidad = findViewById(R.id.edt_cantidad);
        edt_imagen = findViewById(R.id.edt_imagen);
        btn_agregarFruta = findViewById(R.id.btn_agregarFruta);


        btn_agregarFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_nombre.getText().length() > 0 && edt_color.getText().length() > 0){
                    FrutaCon frutaCon = new FrutaCon(getApplicationContext());
                    frutaCon.open();

                    String nombre = edt_nombre.getText().toString();
                    String color = edt_color.getText().toString();
                    Integer cantidad = Integer.parseInt(edt_cantidad.getText().toString());
                    String imagen = edt_imagen.getText().toString();

                    Fruta fruta = new Fruta(nombre, color, cantidad, imagen);
                    frutaCon.agregarFruta(fruta);
                    finish();
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}