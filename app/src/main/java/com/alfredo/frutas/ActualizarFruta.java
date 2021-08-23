package com.alfredo.frutas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ActualizarFruta extends AppCompatActivity implements Dialogo.Custom_dialog {

    TextInputEditText edt_nombreAC, edt_cantidadAC, edt_descripcionAC, edt_beneficiosAC;
    ImageView imageView2;
    String id, nombre, color, cantidad, imagen, beneficios, descripcion, vitaminas;
    Spinner spinner_agregar;
    ChipGroup chipGroupA;


    ArrayAdapter<CharSequence> adapter;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;
    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;
    private static final int URL_REQUEST_CODE = 104;
    private static final int IMAGE_PICK_URL_CODE = 105;


    String [] permisoCamara;
    String [] permisoAlmacenamiento;
    String item_seleccionado;

    ArrayList<String> lista;
    String [] chips;
    String resultado;

    Uri imagenUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_fruta);

        edt_nombreAC = findViewById(R.id.edt_nombreAC);
        edt_cantidadAC = findViewById(R.id.edt_cantidadAC);
        edt_descripcionAC = findViewById(R.id.edt_descripcionAC);
        edt_beneficiosAC = findViewById(R.id.edt_beneficiosAC);
        imageView2 = findViewById(R.id.imageView2);
        spinner_agregar = findViewById(R.id.spinner_agregar);
        chipGroupA = findViewById(R.id.chipGroupA);


        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
        spinner_agregar.setAdapter(adapter);


        spinner_agregar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

        getIntentData();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.barra, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardarMenu:
                if (edt_nombreAC.getText().length() > 0 &&  edt_cantidadAC.getText().length() < 8){
                    FrutaCon frutaCon = new FrutaCon(getApplicationContext());
                    frutaCon.open();

                    String nombre = edt_nombreAC.getText().toString();
                    Integer cantidad = Integer.parseInt(edt_cantidadAC.getText().toString());
                    String descripcion = edt_descripcionAC.getText().toString();
                    String beneficios = edt_beneficiosAC.getText().toString();

                    if (!imagenUri.toString().equals("null")){
                        Fruta fruta = new Fruta(Integer.valueOf(id), nombre, item_seleccionado, cantidad, imagenUri.getPath(), descripcion, beneficios, resultado);
                        frutaCon.actualizarFruta(fruta);

                        Toast.makeText(ActualizarFruta.this, "Se ha actualizado la fruta", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Fruta fruta = new Fruta(Integer.valueOf(id), nombre, item_seleccionado, cantidad, null, descripcion, beneficios, resultado);
                        frutaCon.actualizarFruta(fruta);

                        Toast.makeText(ActualizarFruta.this, "Se ha actualizado la fruta", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else{
                    edt_cantidadAC.requestFocus();
                    edt_cantidadAC.setError("Max. 7 Caracteres");
                    Toast.makeText(ActualizarFruta.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void seleccionarImagen() {
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
                    if (!checarPermisoAlmacenamiento()){
                        solicitarPermisoAlmacenamiento();
                    } else {
                        abrirDialogo();
                    }
                }
            }
        });

        dialog.create().show();
    }

    private void abrirDialogo() {
        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "Ingresa la url");
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
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0){
                    boolean camaraAceptada = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean almacenamientoAceptado = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (camaraAceptada && almacenamientoAceptado){
                        elegirdeCamara();
                    }else {
                        Toast.makeText(this, "Se requieren los permisos de camara y almacenamiento", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0){
                    boolean almacenamientoAceptado = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (almacenamientoAceptado){
                        elegirdeGaleria();
                    } else{
                        Toast.makeText(this, "Se requieren los permisos de almacenamiento", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case URL_REQUEST_CODE: {
                if (grantResults.length > 0){
                    boolean almacenamientoAceptado = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (almacenamientoAceptado) {
                        abrirDialogo();
                    } else {
                        Toast.makeText(this, "Se requieren los permisos de almacenamiento", Toast.LENGTH_LONG).show();
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
            }else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                //crop imagen recibida
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imagenUri = resultUri;
                    //set image
                    imageView2.setImageURI(resultUri);
                }else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    //error
                    Exception exception = result.getError();
                    Toast.makeText(ActualizarFruta.this, ""+ exception, Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    void getIntentData(){
        if (getIntent().hasExtra("nombre") && getIntent().hasExtra("color") && getIntent().hasExtra("cantidad")){
            id = getIntent().getStringExtra("id_fruta");
            nombre = getIntent().getStringExtra("nombre");
            color = getIntent().getStringExtra("color");
            cantidad = getIntent().getStringExtra("cantidad");
            imagenUri = Uri.parse(getIntent().getStringExtra("imagen"));
            descripcion = getIntent().getStringExtra("descripcion");
            beneficios = getIntent().getStringExtra("beneficios");
            vitaminas = getIntent().getStringExtra("vitaminas");

            edt_nombreAC.setText(nombre);
            edt_cantidadAC.setText(cantidad);
            edt_descripcionAC.setText(descripcion);
            edt_beneficiosAC.setText(beneficios);

            //Toast.makeText(ActualizarFruta.this, vitaminas, Toast.LENGTH_SHORT).show();

            String[] array = vitaminas.split("\\|");


            for(String genre : array) {
                Chip chip = (Chip) getLayoutInflater().inflate(R.layout.chip_item, null, false);
                chip.setText(genre);

//                chip.setOnCloseIconClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        chipGroupA.removeView(v);
//                    }
//                });

                chipGroupA.addView(chip);

                StringBuilder str = new StringBuilder();
                str.append(genre+"|");
                resultado = str.toString();
            }
//            StringBuilder str = new StringBuilder();
//            for(int i=0;i<array.length;i++){
//                str.append(array[i]+"|");
//            }
//            resultado = str.toString();

            //Toast.makeText(ActualizarFruta.this, array[2], Toast.LENGTH_SHORT).show();

            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner));
            spinner_agregar.setAdapter(adapter);
            int position = adapter.getPosition(color);


            spinner_agregar.setSelection(((ArrayAdapter<String>)spinner_agregar.getAdapter()).getPosition(color));
//            combo_categoria.setSelection(Integer.parseInt(getIntent().getStringExtra("categoria")) - 1);

            if (imagenUri == null){
                imageView2.setImageResource(R.drawable.ic_datos);
            } else {
                imageView2.setImageURI(imagenUri);
            }

        }
    }

    @Override
    public void aplicarImagen(String image) {
        Glide.with(ActualizarFruta.this)
                .load(image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_datos)
                .into(imageView2);

        GuardarImagen(image);
    }

    public void GuardarImagen(String url) {
        Glide.with(this).asBitmap().load(url).into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                try {
                    File mydir = new File(Environment.getExternalStorageDirectory() + "/pictures");
                    if (!mydir.exists()) {
                        mydir.mkdirs();
                    }

                    imagenUri = Uri.parse(mydir.getAbsolutePath() + File.separator + System.currentTimeMillis() + ".jpg");
                    FileOutputStream outputStream = new FileOutputStream(String.valueOf(imagenUri));

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(AgregarFruta.this, "Guardado", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onLoadCleared(Drawable placeholder) {
            }
        });
    }
}
