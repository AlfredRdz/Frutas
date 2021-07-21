package com.alfredo.frutas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        View view = layoutInflater.inflate(R.layout.fila, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Fruta fruta = frutas.get(position);
        holder.txt_nombre.setText(fruta.getNombre());
        holder.txt_color.setText(fruta.getColor());
        holder.txt_cantidad.setText(Integer.toString(fruta.getCantidad()));


        holder.fila.setOnClickListener(new View.OnClickListener() {
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
        TextView txt_nombre, txt_cantidad, txt_color;
        LinearLayout fila;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombre = itemView.findViewById(R.id.txt_nombre);
            txt_cantidad = itemView.findViewById(R.id.txt_cantidad);
            txt_color = itemView.findViewById(R.id.txt_color);
            fila = itemView.findViewById(R.id.fila);
        }
    }
}
