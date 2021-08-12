package com.alfredo.frutas;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dialogo extends AppCompatDialogFragment {
    TextInputEditText edt_urlD;

    Custom_dialog custom_dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle saveInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);


        builder.setView(view);

        ImageView imageViewD = view.findViewById(R.id.imageViewD);
        edt_urlD = view.findViewById(R.id.edt_urlD);
        Button btn_dialogo = view.findViewById(R.id.btn_dialogo);

        btn_dialogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String url = "https://coca-colafemsa.com/wp-content/uploads/2019/11/1.png";
                Glide.with(Dialogo.this)
                        .load(edt_urlD.getText().toString())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_datos)
                        .into(imageViewD);
            }
        });

        builder.setPositiveButton("Seleccionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String imagen = edt_urlD.getText().toString();
                custom_dialog.aplicarImagen(imagen);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        custom_dialog = (Custom_dialog) context;
    }

    public interface Custom_dialog{
        void aplicarImagen(String image);
    }

}
