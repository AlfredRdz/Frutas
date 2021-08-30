package com.alfredo.frutas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alfredo.frutas.databinding.ChipItemBinding;

import java.util.ArrayList;

public class ChipAdapter extends RecyclerView.Adapter<ChipAdapter.ViewHolder>{

    private ArrayList<Chip> chipArrayList;
    private ChipOnClickListener chipOnClickListener;

    public ChipAdapter(ArrayList<Chip> chipArrayList, ChipOnClickListener chipOnClickListener){
        this.chipArrayList = chipArrayList;
        this.chipOnClickListener = chipOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChipItemBinding binding = ChipItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chip item = chipArrayList.get(position);
        holder.bind(item, chipOnClickListener);

    }

    @Override
    public int getItemCount() {
        return chipArrayList != null ? chipArrayList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ChipItemBinding binding;

        public ViewHolder(@NonNull ChipItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(Chip chip, ChipOnClickListener chipOnClickListener){
            binding.getRoot().setOnClickListener(view -> chipOnClickListener.onClick(chip));
            binding.chipItem.setText(chip.getNombre());
            binding.chipItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (binding.chipItem.isChecked()){
                        Toast.makeText(view.getContext(), chip.getNombre(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
