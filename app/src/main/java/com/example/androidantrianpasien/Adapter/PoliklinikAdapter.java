package com.example.androidantrianpasien.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.DokterActivity;
import com.example.androidantrianpasien.Model.ItemPoliklinik;
import com.example.androidantrianpasien.R;

import java.util.ArrayList;
import java.util.List;

public class PoliklinikAdapter extends RecyclerView.Adapter<PoliklinikAdapter.ViewHolder> {

    Context context;
    ArrayList<ItemPoliklinik> list;

    public PoliklinikAdapter(ArrayList<ItemPoliklinik> item, Context context) {
        this.list = item;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewPoli;
        LinearLayout wrap;

        ItemClickListener itemClick;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewPoli = itemView.findViewById(R.id.textViewPoli);
            wrap = itemView.findViewById(R.id.wrap);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClick.onItemClick(view, getLayoutPosition());
        }
    }

    interface ItemClickListener {
        void onItemClick(View view, int layoutPosition);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poli, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemPoliklinik item = list.get(position);

        holder.textViewPoli.setText(item.getNama_poli());
        holder.wrap.setOnClickListener(view -> {
            Intent a = new Intent(context, DokterActivity.class);
            a.putExtra("kode", item.getKode_poli());
            a.putExtra("nama", item.getNama_poli());
            context.startActivity(a);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
