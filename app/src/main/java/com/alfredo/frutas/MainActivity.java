package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Usuario;
import com.alfredo.frutas.conexion.UsuarioCon;
import com.alfredo.frutas.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //TextInputEditText edt_usuario, edt_contraseña;
    //TextView textView4;
    //Button btn_ingresar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //edt_usuario = findViewById(R.id.edt_usuario);
        //edt_contraseña = findViewById(R.id.edt_contraseña);
        //btn_ingresar = findViewById(R.id.btn_ingresar);
        //textView4 = findViewById(R.id.textView4);


        binding.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registro.class));
                //finish();
            }
        });



        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtUsuario.getText().length() > 0 && binding.edtContraseA.getText().length() > 0){
                    UsuarioCon usuarioCon = new UsuarioCon(getApplicationContext());
                    usuarioCon.open();

                    String nombre = binding.edtUsuario.getText().toString();
                    String contraseña = binding.edtContraseA.getText().toString();

                    boolean comprobar = usuarioCon.login(nombre, contraseña);

                    if (comprobar == true){
                        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                        editor = preferences.edit();
                        editor.putString("nombre" , nombre);
                        editor.putString("contraseña", contraseña);
                        editor.commit();

                        Intent intent = new Intent(MainActivity.this, Listado.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Datos incorrectos" , Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Debes ingresar todos los campos" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}