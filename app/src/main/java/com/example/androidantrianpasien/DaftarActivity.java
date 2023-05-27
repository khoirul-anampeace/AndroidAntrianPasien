package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidantrianpasien.Fragment.FragmentBaru;
import com.example.androidantrianpasien.Fragment.FragmentLama;

public class DaftarActivity extends AppCompatActivity {

    private TextView FullView;

    String poli, poli_nama, dokter, dokter_nama, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        FullView = findViewById(R.id.FullView);

        poli = getIntent().getStringExtra("poli");
        poli_nama = getIntent().getStringExtra("poli_nama");
        dokter = getIntent().getStringExtra("dokter");
        dokter_nama = getIntent().getStringExtra("dokter_nama");
        tanggal = getIntent().getStringExtra("tanggal");

        String displayText = "Nama Poli: " + poli_nama + "\nTanggal CheckUp: " + tanggal + "\nNama Dokter : " + dokter_nama;
        FullView.setText(displayText);

        Button btnbaru = findViewById(R.id.baru);
        btnbaru.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, FragmentBaru.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name").commit();
        });

        Button btnlama = findViewById(R.id.lama);
        btnlama.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, FragmentLama.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name").commit();
        });
    }
}
