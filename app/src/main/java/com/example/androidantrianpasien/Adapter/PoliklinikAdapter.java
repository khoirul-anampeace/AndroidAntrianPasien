package com.example.androidantrianpasien.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.R;

import java.util.List;

public class PoliklinikAdapter extends RecyclerView.Adapter<PoliklinikAdapter.ViewHolder> {
    private List<String> poliklinikList;
    private ItemClickListener itemClickListener;

    public PoliklinikAdapter(List<String> poliklinikList, ItemClickListener itemClickListener) {
        this.poliklinikList = poliklinikList;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewPoli;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPoli = itemView.findViewById(R.id.textViewPoli);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                String poliklinik = poliklinikList.get(position);
                itemClickListener.onItemClick(poliklinik);
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(String poliklinik);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_poli, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String poliklinik = poliklinikList.get(position);
        holder.textViewPoli.setText(poliklinik);
    }

    @Override
    public int getItemCount() {
        return poliklinikList.size();
    }
}
