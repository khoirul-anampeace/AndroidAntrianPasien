package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidantrianpasien.Fragment.FragmentBaru;
import com.example.androidantrianpasien.Fragment.FragmentLama;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Button btnbaru = findViewById(R.id.baru);
        btnbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewB, FragmentBaru.class, null)
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
                        .replace(R.id.fragmentContainerViewB, FragmentLama.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name").commit();
            }
        });
    }
}