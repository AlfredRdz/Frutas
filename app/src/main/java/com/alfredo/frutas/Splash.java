package com.alfredo.frutas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sh = getSharedPreferences("preferences", MODE_PRIVATE);
        String usuario = sh.getString("nombre", "");
        String contraseña = sh.getString("contraseña", "");


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {


                if (usuario.length() > 0 && contraseña.length() > 0) {
                    //Toast.makeText(getApplicationContext(), "Sesion Iniciada", Toast.LENGTH_LONG).show();
                    // startActivity(new Intent(this, Listado.class));

                    Intent intent = new Intent(Splash.this, Listado.class);
                    startActivity(intent);
                    finish();

                } else {
                    //Toast.makeText(getApplicationContext(), "Inicia Sesion", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Splash.this, MainActivity.class));
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);




    }
}