package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.androidantrianpasien.Adapter.DokterAdapter;
import java.util.ArrayList;
import java.util.List;

public class DokterActivity extends AppCompatActivity {

    private TextView TanggalView;
    private RecyclerView recyclerView;
    private DokterAdapter dokterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        TanggalView = findViewById(R.id.TanggalView);
        recyclerView = findViewById(R.id.RvDokter);

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");
        String buttonName = intent.getStringExtra("buttonName");

        String displayText = "NamaPoli: " + buttonName + "\nTanggal CheckUp: " + selectedDate;
        TanggalView.setText(displayText);

        List<String> dataList = new ArrayList<>();
        dataList.add(getString(R.string.Dr1));
        dataList.add(getString(R.string.Dr2));
        dataList.add(getString(R.string.Dr3));
        dataList.add(getString(R.string.Dr4));
        dataList.add(getString(R.string.Dr5));

        dokterAdapter = new DokterAdapter(dataList, selectedDate);
        recyclerView.setAdapter(dokterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}