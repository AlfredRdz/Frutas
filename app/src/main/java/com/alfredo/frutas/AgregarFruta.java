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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.google.android.material.textfield.TextInputLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AgregarFruta extends AppCompatActivity implements Dialogo.Custom_dialog{
    TextInputEditText edt_nombre, edt_cantidad, edt_descripcion, edt_beneficios;
    ImageView imageViewFruta;
    Spinner spinner_fruta;
    Button btn_chips;
    ChipGroup chipGroup3, chipGroupK;
    Chip chipA, chipC, chipD, chipE, chipK, chipB1;

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
    String [] chips;
    String resultado;

    List<String> lista = new ArrayList<>();

    //String fileUri;
    Uri imagenUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_cantidad = findViewById(R.id.edt_cantidad);
        edt_descripcion = findViewById(R.id.edt_descripcion);
        edt_beneficios = findViewById(R.id.edt_beneficios);
        imageViewFruta = findViewById(R.id.imageViewFruta);
        spinner_fruta = findViewById(R.id.spinner_fruta);
        chipGroupK = findViewById(R.id.chipGroupK);
        chipA = findViewById(R.id.chipA);
        chipC = findViewById(R.id.chipC);
        chipD = findViewById(R.id.chipD);
        chipE = findViewById(R.id.chipE);
        chipK = findViewById(R.id.chipK);
        chipB1 = findViewById(R.id.chipB1);


//        if (getIntent().hasExtra("lista")){
//            lista = (ArrayList<Chips>) getIntent().getSerializableExtra("lista");
//            //Toast.makeText(AgregarFruta.this, lista.toString(), Toast.LENGTH_SHORT).show();
//
//            chips = lista.toArray(new String[lista.size()]);
//            for(String genre : chips) {
//                Chip chip = (Chip) getLayoutInflater().inflate(R.layout.chip_item, null, false);
//                chip.setText(genre);
//
//                chip.setOnCloseIconClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        chipGroup3.removeView(v);
//                    }
//                });
//
//                chipGroup3.addView(chip);
//            }
//
//
//            ///////////////////////////////////////
//            StringBuilder str = new StringBuilder();
//            for(int i=0;i<chips.length;i++){
//                str.append(chips[i]+"|");
//            }
//            resultado = str.toString();
//            //Toast.makeText(AgregarFruta.this, resultado, Toast.LENGTH_SHORT).show();
//        }

//        String[] genres = {"Thriller", "Comedy", "Adventure"};
//        for(String genre : genres) {
//            Chip chip = (Chip) getLayoutInflater().inflate(R.layout.chip_item, null, false);
//            chip.setText(genre);
//
//            chip.setOnCloseIconClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    chipGroup3.removeView(v);
//                }
//            });
//
//            chipGroup3.addView(chip);
//        }

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
                    String descripcion = edt_descripcion.getText().toString();
                    String beneficios = edt_beneficios.getText().toString();

                    /////////////////////////////////////////////////////////

                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i< chipGroupK.getChildCount(); i++){
                        Chip chip = (Chip) chipGroupK.getChildAt(i);
                        if (chip.isChecked()){
                            if (i < chipGroupK.getChildCount() - 1 ){
                                builder.append(chip.getText()).append("|");
                                lista.add(chip.getText().toString());
                            } else {
                                builder.append(chip.getText());
                                lista.add(chip.getText().toString());
                            }
                        }
                        //chipGroup.addView(chip);
                    }

                    resultado = builder.toString();

                    //////////////////////////////////////////////////////////

                    if (imagenUri != null){
                        Fruta fruta = new Fruta(nombre, item_seleccionado, cantidad, imagenUri.getPath(), descripcion, beneficios, resultado);
                        frutaCon.agregarFruta(fruta);
                        finish();
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    } else {
                        Fruta fruta = new Fruta(nombre, item_seleccionado, cantidad, descripcion, beneficios, resultado);
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

                    abrirDialogo();
                }
            }
        });

        dialog.create().show();
    }

    private void abrirDialogo() {

        Dialogo dialogo = new Dialogo();
        dialogo.show(getSupportFragmentManager(), "Ingresa la url");

//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, "Titulo de la imagen");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "Descripcion de la imagen");
//
//        imagenUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imagenUri);
//        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE);
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
                        abrirDialogo();
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

        SaveImage(image);
    }

    public void SaveImage(String url) {
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