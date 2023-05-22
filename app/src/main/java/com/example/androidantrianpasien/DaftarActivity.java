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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        FullView = findViewById(R.id.FullView);

        String buttonName = getIntent().getStringExtra("buttonName");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        String displayText = "Nama Poli: " + buttonName + "\nTanggal CheckUp: " + selectedDate + "\nNama Dokter : " + buttonName;
        FullView.setText(displayText);

        Button btnbaru = findViewById(R.id.baru);
        btnbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, FragmentBaru.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
            }
        });

        Button btnlama = findViewById(R.id.lama);
        btnlama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, FragmentLama.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
            }
        });
    }
}
