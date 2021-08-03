package com.alfredo.frutas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alfredo.frutas.conexion.Fruta;
import com.alfredo.frutas.conexion.FrutaCon;

import java.util.List;

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.MyViewHolder> {

    List<Fruta> frutas;
    Context context;
    FrutaCon frutaCon;

    public FrutaAdapter(List<Fruta> frutas, Context context){
        this.frutas = frutas;
        this.context = context;
        frutaCon = new FrutaCon(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_item, parent, false);
        return new MyViewHolder(view);
    }

    private Context getContext() {
        return context;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Fruta fruta = frutas.get(position);
        holder.txt_nombre.setText(fruta.getNombre());
        holder.txt_cantidad.setText(Integer.toString(fruta.getCantidad()));

        String imagen = fruta.getImagen();

        if (imagen == null){
            holder.imageView.setImageResource(R.drawable.ic_datos);
        } else {
            holder.imageView.setImageURI(Uri.parse(imagen));
        }

        holder.card_id.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                System.out.println("POSITION " +position);
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Eliminar")
                        .setMessage("¿Seguro que quieres eliminar?")
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FrutaCon frutaCon = new FrutaCon(v.getContext());
                                frutaCon.open();

                                frutaCon.eliminarFruta(fruta.getId_fruta());
                                Toast.makeText(v.getContext(), "Se ha eliminado la fruta", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            }

        });

        holder.card_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActualizarFruta.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id_fruta", String.valueOf(fruta.getId_fruta()));
                intent.putExtra("nombre", String.valueOf(fruta.getNombre()));
                intent.putExtra("color", String.valueOf(fruta.getColor()));
                intent.putExtra("cantidad", String.valueOf(fruta.getCantidad()));
                intent.putExtra("imagen", String.valueOf(fruta.getImagen()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return frutas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nombre, txt_cantidad;
        ImageView imageView;
        CardView card_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre = itemView.findViewById(R.id.txt_cardNombre);
            txt_cantidad = itemView.findViewById(R.id.txt_cardCantidad);
            imageView = itemView.findViewById(R.id.imageView_Card);
            card_id = itemView.findViewById(R.id.card_id);
        }
    }
}
