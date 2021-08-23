package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Usuario;
import com.alfredo.frutas.conexion.UsuarioCon;
import com.alfredo.frutas.databinding.ActivityRegistroBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Registro extends AppCompatActivity {

    private ActivityRegistroBinding binding;

    TextInputEditText edt_reusuario, edt_recontraseña, edt_repetircontraseña;
    TextInputLayout textInputLayout6, textInputLayout7, textInputLayout3;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edt_reusuario = findViewById(R.id.edt_reusuario);
        edt_recontraseña = findViewById(R.id.edt_recontraseña);
        edt_repetircontraseña = findViewById(R.id.edt_repetircontraseña);
        btn_registrar = findViewById(R.id.btn_registrar);

        textInputLayout3 = findViewById(R.id.textInputLayout3);
        textInputLayout6 = findViewById(R.id.textInputLayout6);
        textInputLayout7 = findViewById(R.id.textInputLayout7);


        edt_reusuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 21){
                    textInputLayout3.setError(null);
                } else {
                    textInputLayout3.setError("Solo se permiten 20 caracteres");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_recontraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 7){
                    textInputLayout6.setError(null);
                } else {
                    textInputLayout6.setError("Deben ser minimo 8 caracteres");
                    textInputLayout6.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_repetircontraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(edt_recontraseña.getText().toString())){
                    Toast.makeText(getApplicationContext(), "coincidn", Toast.LENGTH_SHORT).show();
                    textInputLayout7.setError(null);
                } else {
                    Toast.makeText(getApplicationContext(), "no coiciden", Toast.LENGTH_SHORT).show();
                    textInputLayout7.setError("No coiciden las contraseñas");
                }
//                if (s.length() > 7){
//                    textInputLayout7.setError(null);
//                } else {
//                    textInputLayout7.setError("Deben ser minimo 8 caracteres");
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_reusuario.getText().length() > 0 && edt_reusuario.getText().length() < 21 && edt_recontraseña.getText().length() > 7 && edt_repetircontraseña.getText().length() > 7){
                    if (edt_recontraseña.getText().toString().equals(edt_repetircontraseña.getText().toString())){
                        UsuarioCon usuarioCon = new UsuarioCon(getApplicationContext());
                        usuarioCon.open();
                        String usuario = edt_reusuario.getText().toString();
                        String contraseña = edt_recontraseña.getText().toString();
                        Usuario usuarioAgregar = new Usuario(usuario, contraseña);


                        //long id = usuarioCon.agregarUsuario(usuarioAgregar);


                        boolean comprobar = usuarioCon.login(usuario);

                        if (comprobar == false){
                            usuarioCon.agregarUsuario(usuarioAgregar);

                            SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("nombre" , usuario);
                            editor.putString("contraseña", contraseña);
                            editor.commit();


                            startActivity(new Intent(Registro.this, Listado.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario ya existe", Toast.LENGTH_LONG).show();
                        }


                    }else {
                        Toast.makeText(getBaseContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
//                        textInputLayout6.setError("Las Contraseñas no coinciden");
//                        textInputLayout7.setError("Las Contraseñas no coinciden");
                    }
                }else {
                    //Toast.makeText(getBaseContext(), "Debes llenar todos los campos", Toast.LENGTH_LONG).show();
                    //edt_recontraseña.setError("Fallo");
//                    textInputLayout6.setError("Deben ser minimo 8 caracteres");
//                    textInputLayout7.setError("Deben ser minimo 8 caracteres");
                }
            }
        });
    }
}