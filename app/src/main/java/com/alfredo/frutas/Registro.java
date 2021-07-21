package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Usuario;
import com.alfredo.frutas.conexion.UsuarioCon;

public class Registro extends AppCompatActivity {

    EditText edt_reusuario, edt_recontraseña, edt_repetircontraseña;
    Button btn_registrar, btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt_reusuario = findViewById(R.id.edt_reusuario);
        edt_recontraseña = findViewById(R.id.edt_recontraseña);
        edt_repetircontraseña = findViewById(R.id.edt_repetircontraseña);
        btn_registrar = findViewById(R.id.btn_registrar);
        btn_iniciar = findViewById(R.id.btn_iniciar);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_reusuario.getText().length() > 0 && edt_recontraseña.getText().length() > 0 && edt_repetircontraseña.getText().length() > 0){
                    if (edt_recontraseña.getText().toString().equals(edt_repetircontraseña.getText().toString())){
                        UsuarioCon usuarioCon = new UsuarioCon(getApplicationContext());
                        usuarioCon.open();
                        String usuario = edt_reusuario.getText().toString();
                        String contraseña = edt_recontraseña.getText().toString();
                        Usuario usuarioAgregar = new Usuario(usuario, contraseña);

                        usuarioCon.agregarUsuario(usuarioAgregar);
                        finish();
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getBaseContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(), "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}