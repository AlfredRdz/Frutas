package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alfredo.frutas.conexion.AppDataBase;
import com.alfredo.frutas.datamodel.Usuario;
import com.alfredo.frutas.databinding.ActivityMainBinding;
import com.alfredo.frutas.interfaces.LoginMVP;
import com.alfredo.frutas.presenter.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements LoginMVP.View {

    private static final String TAG = "View";
    private ActivityMainBinding binding;
    private LoginMVP.Presenter presenter;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //LoginPresenter.getPresenter(MainActivity.this).setView(this);

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

                    String nombre = binding.edtUsuario.getText().toString();
                    String contraseña = binding.edtContraseA.getText().toString();

                    getPresenter().getContext(MainActivity.this);
                    getPresenter().executeLogin(nombre, contraseña);
                    //LoginPresenter.getPresenter(MainActivity.this).executeLogin(nombre, contraseña);

//                    AppDataBase appDataBase = AppDataBase.getInstance(getApplicationContext());
//
//                    Usuario usuario = appDataBase.usuarioDao().login(nombre, contraseña);
//
//
//                    if (usuario != null){
//                        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
//                        editor = preferences.edit();
//                        editor.putString("nombre" , nombre);
//                        editor.putString("contraseña", contraseña);
//                        editor.commit();
//
//                        finish();
//                        Intent intent = new Intent(MainActivity.this, Listado.class);
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Datos incorrectos" , Toast.LENGTH_LONG).show();
//                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Debes ingresar todos los campos" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private LoginMVP.Presenter getPresenter () {
        if (presenter == null) {
            presenter = new LoginPresenter(this);
        }
        return presenter;
    }

    @Override
    public void showProgressBar(boolean isShowing) {
        Log.i(TAG, "showProgressBar: " + isShowing);
        binding.progressBar.setVisibility(!isShowing ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSuccess() {
        finish();
        Intent intent = new Intent(MainActivity.this, Listado.class);
        startActivity(intent);
    }
}