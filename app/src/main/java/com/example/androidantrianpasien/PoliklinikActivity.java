package com.example.androidantrianpasien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PoliklinikActivity extends AppCompatActivity {
    private Calendar calendar;
    private Button button1, button2, button3, button4, button5, ButtonAntrean;
    private String buttonName;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliklinik);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        ButtonAntrean = findViewById(R.id.buttonAntrean);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonName = getResources().getString(R.string.Poli1);
                ;
                showDatePickerDialog();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonName = getResources().getString(R.string.Poli2);
                ;
                showDatePickerDialog();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonName = getResources().getString(R.string.Poli3);
                ;
                showDatePickerDialog();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonName = getResources().getString(R.string.Poli4);
                ;
                showDatePickerDialog();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonName = getResources().getString(R.string.Poli5);
                ;
                showDatePickerDialog();
            }
        });

        ButtonAntrean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoliklinikActivity.this, AntreanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(Calendar.YEAR, year);
                selectedCalendar.set(Calendar.MONTH, monthOfYear);
                selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // validasi tanggal
                if (selectedCalendar.before(Calendar.getInstance())) {
                    Toast.makeText(PoliklinikActivity.this, "Gabisa Ke Masa Lalu coy", Toast.LENGTH_SHORT).show();
                } else {
                    String selectedDate = dateFormat.format(selectedCalendar.getTime());
                    Intent intent = new Intent(PoliklinikActivity.this, DokterActivity.class);
                    intent.putExtra("selectedDate", selectedDate);
                    intent.putExtra("buttonName", buttonName);
                    startActivity(intent);
                }
            }
        };
        new DatePickerDialog(PoliklinikActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }
}
