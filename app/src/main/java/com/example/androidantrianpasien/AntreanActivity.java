package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AntreanActivity extends AppCompatActivity {

    private Button buttonkembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrean);

        buttonkembali = findViewById(R.id.buttonback);

        buttonkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AntreanActivity.this, PoliklinikActivity.class);
                startActivity(intent);
            }
        });
    }
}