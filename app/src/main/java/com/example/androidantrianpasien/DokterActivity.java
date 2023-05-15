package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DokterActivity extends AppCompatActivity {

    private TextView TanggalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        TanggalView = findViewById(R.id.TanggalView);

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");
        String buttonName = intent.getStringExtra("buttonName");

        String displayText = "NamaPoli: " + buttonName + "\nTanggal CheckUp: " + selectedDate;
        TanggalView.setText(displayText);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", button1.getText().toString());
                intent.putExtra("tanggalViewText", TanggalView.getText().toString());
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", button2.getText().toString());
                intent.putExtra("tanggalViewText", TanggalView.getText().toString());
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", button3.getText().toString());
                intent.putExtra("tanggalViewText", TanggalView.getText().toString());
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", button4.getText().toString());
                intent.putExtra("tanggalViewText", TanggalView.getText().toString());
                startActivity(intent);
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DokterActivity.this, DaftarActivity.class);
                intent.putExtra("selectedDate", selectedDate);
                intent.putExtra("buttonName", button5.getText().toString());
                intent.putExtra("tanggalViewText", TanggalView.getText().toString());
                startActivity(intent);
            }
        });
    }
}
