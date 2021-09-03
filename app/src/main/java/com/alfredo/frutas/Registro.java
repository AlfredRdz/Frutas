package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.databinding.ActivityRegistroBinding;
import com.alfredo.frutas.interfaces.RegistroMVP;
import com.alfredo.frutas.presenter.RegistroPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Registro extends AppCompatActivity implements RegistroMVP.View {

    private ActivityRegistroBinding binding;

    TextInputEditText edt_reusuario, edt_recontraseña, edt_repetircontraseña;
    TextInputLayout textInputLayout6, textInputLayout7, textInputLayout3;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RegistroPresenter.getPresenter(Registro.this).setView(this);

        binding.edtReusuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 21){
                    binding.textInputLayout3.setError(null);
                } else {
                    binding.textInputLayout3.setError("Solo se permiten 20 caracteres");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edtRecontraseA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 7){
                    binding.textInputLayout6.setError(null);
                } else {
                    binding.textInputLayout6.setError("Deben ser minimo 8 caracteres");
                    binding.textInputLayout6.setEndIconMode(TextInputLayout.END_ICON_PASSWORD_TOGGLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.edtRepetircontraseA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(binding.edtRecontraseA.getText().toString())){
                    Toast.makeText(getApplicationContext(), "coincidn", Toast.LENGTH_SHORT).show();
                    binding.textInputLayout7.setError(null);
                } else {
                    Toast.makeText(getApplicationContext(), "no coiciden", Toast.LENGTH_SHORT).show();
                    binding.textInputLayout7.setError("No coiciden las contraseñas");
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

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.edtReusuario.getText().length() > 0 && binding.edtReusuario.getText().length() < 21 && binding.edtRecontraseA.getText().length() > 7 && binding.edtRepetircontraseA.getText().length() > 7){
                    if (binding.edtRecontraseA.getText().toString().equals(binding.edtRepetircontraseA.getText().toString())){

                        String usuario = binding.edtReusuario.getText().toString();
                        String contraseña = binding.edtRecontraseA.getText().toString();

                        AppDataBase appDataBase = AppDataBase.getInstance(getApplicationContext());
                        Usuario comprobar = appDataBase.usuarioDao().comprobar(usuario);

                        if (comprobar == null){
                            //guardarUsuario(binding.edtReusuario.getText().toString(), binding.edtRecontraseA.getText().toString());
                            RegistroPresenter.getPresenter(Registro.this).executeRegister(usuario, contraseña);
                        } else {
                            Toast.makeText(getApplicationContext(), "Usuario ya existe", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(getBaseContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

//    private void guardarUsuario(String nombre, String contraseña){
//        AppDataBase appDataBase = AppDataBase.getInstance(this.getApplicationContext());
//
//        Usuario usuario = new Usuario();
//        usuario.setUsuario(nombre);
//        usuario.setContraseña(contraseña);
//        appDataBase.usuarioDao().insertUsuario(usuario);
//
//        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("nombre" , nombre);
//        editor.putString("contraseña", contraseña);
//        editor.commit();
//
//        startActivity(new Intent(Registro.this, Listado.class));
//        finish();
//        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onSuccess(String message) {
        startActivity(new Intent(Registro.this, Listado.class));
        finish();
        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRegister(String name, String password) {
        SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre" , name);
        editor.putString("contraseña", password);
        editor.commit();

        startActivity(new Intent(Registro.this, Listado.class));
        finish();
    }
}