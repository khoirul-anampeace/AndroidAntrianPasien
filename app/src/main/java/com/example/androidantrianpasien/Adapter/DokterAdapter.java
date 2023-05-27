package com.example.androidantrianpasien.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.Model.ItemDokter;
import com.example.androidantrianpasien.R;

import java.util.ArrayList;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.ViewHolder> {

    Context context;
    ArrayList<ItemDokter> list;
    int checkedPosition = -1;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewDokter;
        LinearLayout wrap;
        ItemClickListener itemClick;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDokter = itemView.findViewById(R.id.textViewDokter);
            wrap = itemView.findViewById(R.id.itemLayout);
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

    public ItemDokter getSelected() {
        if (checkedPosition != -1) {
            return list.get(checkedPosition);
        }
        return null;
    }

    public DokterAdapter(ArrayList<ItemDokter> item, Context context) {
        this.list = item;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ItemDokter item = list.get(position);

        holder.textViewDokter.setText(item.getNama_dokter());

        holder.wrap.setOnClickListener(v -> {
            notifyItemChanged(checkedPosition);
            checkedPosition = position;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
