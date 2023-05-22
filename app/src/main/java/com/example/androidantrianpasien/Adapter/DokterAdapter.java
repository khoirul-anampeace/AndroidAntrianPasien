package com.example.androidantrianpasien.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.DaftarActivity;
import com.example.androidantrianpasien.R;

import java.util.List;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.ViewHolder> {

    private List<String> dataList;
    private String selectedDate;

    public DokterAdapter(List<String> dataList, String selectedDate) {
        this.dataList = dataList;
        this.selectedDate = selectedDate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String buttonName = dataList.get(position);
        holder.textViewDokter.setText(buttonName);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", buttonName);
                intent.putExtra("tanggalViewText", holder.textViewDokter.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemLayout;
        TextView textViewDokter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.itemLayout);
            textViewDokter = itemView.findViewById(R.id.textViewDokter);
        }
    }
}
