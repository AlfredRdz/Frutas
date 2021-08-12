package com.alfredo.frutas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class AgregarFruta extends AppCompatActivity implements Dialogo.Custom_dialog{
    TextInputEditText edt_nombre, edt_cantidad;
    ImageView imageViewFruta;
    Spinner spinner_fruta;

    ArrayAdapter<String> adapter;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    private static final int URL_REQUEST_CODE = 104;
    private static final int IMAGE_PICK_URL_CODE = 105;


    String [] permisoCamara;
    String [] permisoAlmacenamiento;
    String item_seleccionado;


    Uri imagenUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_cantidad = findViewById(R.id.edt_cantidad);
        imageViewFruta = findViewById(R.id.imageViewFruta);
        spinner_fruta = findViewById(R.id.spinner_fruta);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
        spinner_fruta.setAdapter(adapter);


        spinner_fruta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_seleccionado = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        permisoCamara = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        permisoAlmacenamiento = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imageViewFruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeleccionarImagen();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardarMenu:
                if (edt_nombre.getText().length() > 0 && edt_cantidad.getText().length() > 0 && edt_cantidad.getText().length() <8){
                    FrutaCon frutaCon = new FrutaCon(getApplicationContext());
                    frutaCon.open();

                    String nombre = edt_nombre.getText().toString();
                    Integer cantidad = Integer.parseInt(edt_cantidad.getText().toString());

                    if (imagenUri != null){
                        Fruta fruta = new Fruta(nombre, item_seleccionado, cantidad, imagenUri.getPath());
                        frutaCon.agregarFruta(fruta);
                        finish();
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    } else {
                        Fruta fruta = new Fruta(nombre, item_seleccionado, cantidad);
                        frutaCon.agregarFruta(fruta);
                        finish();
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "seleccionar un color: " + item_seleccionado, Toast.LENGTH_LONG).show();
                    }
                }else{
                    edt_cantidad.requestFocus();
                    edt_cantidad.setError("Max. 7 Caracteres");
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    private void SeleccionarImagen() {
        //mostrar dialog
        String [] opciones = {"Tomar Foto", "Galeria", "Url"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Selecciona");
        dialog.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    if (!checarPermisoCamara()){
                        solicitarPermisoCamara();
                    }else {
                        elegirdeCamara();
                    }
                } else if (which == 1){
                    if (!checarPermisoAlmacenamiento()){
                        solicitarPermisoAlmacenamiento();
                    } else {
                        elegirdeGaleria();
                    }
                } else if (which == 2){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarFruta.this);
//                    builder.setTitle("url");
//                    builder.setMessage("Ingresa la url");
//                    builder.create().show();

                    openDialog();
                }
            }
        });

        dialog.create().show();
    }

    private void openDialog() {

        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "Ingresa la url");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Titulo de la imagen");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion de la imagen");

        imagenUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private void elegirdeGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK);
        galeria.setType("image/*");
        startActivityForResult(galeria, IMAGE_PICK_GALLERY_CODE);
    }

    private void elegirdeCamara() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Titulo de la imagen");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion de la imagen");

        imagenUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
    }

    private void solicitarPermisoAlmacenamiento() {
        ActivityCompat.requestPermissions(this, permisoAlmacenamiento, STORAGE_REQUEST_CODE);
    }

    private boolean checarPermisoAlmacenamiento() {
        boolean resultado = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return resultado;
    }

    private void solicitarPermisoCamara() {
        ActivityCompat.requestPermissions(this, permisoCamara, CAMERA_REQUEST_CODE);
    }

    private boolean checarPermisoCamara() {
        boolean resultado = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);

        boolean resultado1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return  resultado && resultado1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean camaraAceptada = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean almacenamientoAceptado = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (camaraAceptada && almacenamientoAceptado) {
                        elegirdeCamara();
                    } else {
                        Toast.makeText(this, "Se requieren los permisos de camara y almacenamiento", Toast.LENGTH_LONG);
                    }
                }
            }
            break;
            case STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    boolean almacenamientoAceptado = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (almacenamientoAceptado) {
                        elegirdeGaleria();
                    } else {
                        Toast.makeText(this, "Se requieren los permisos de almacenamiento", Toast.LENGTH_LONG);
                    }
                }
            }
            break;
            case URL_REQUEST_CODE: {
                if (grantResults.length > 0){
                    boolean almacenamientoAceptado = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (almacenamientoAceptado) {
                        openDialog();
                    } else {
                        Toast.makeText(this, "Se requieren los permisos de almacenamiento", Toast.LENGTH_LONG);
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            //imagen es elegida
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                //elegida de la galerai
                //crop image
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            }else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                //elegida de camara
                //crop iamge
                CropImage.activity(imagenUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            } else if (requestCode == IMAGE_PICK_URL_CODE){
                Toast.makeText(AgregarFruta.this, "entra en la IMAGE_PICK_URL_CODE", Toast.LENGTH_LONG).show();
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                //crop imagen recibida
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imagenUri = resultUri;
                    //set image
                    imageViewFruta.setImageURI(resultUri);
                }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    //error
                    Exception exception = result.getError();
                    Toast.makeText(AgregarFruta.this, ""+ exception, Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    @Override
    public void aplicarImagen(String image) {
        Glide.with(AgregarFruta.this)
                .load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_datos)
                .into(imageViewFruta);


    }
}