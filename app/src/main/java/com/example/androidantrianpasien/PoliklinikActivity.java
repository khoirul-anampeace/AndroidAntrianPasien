package com.example.androidantrianpasien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.Adapter.PoliklinikAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PoliklinikActivity extends AppCompatActivity implements PoliklinikAdapter.ItemClickListener {
    private Calendar calendar;
    private Button ButtonAntrean;
    private String buttonName;
    private SimpleDateFormat dateFormat;
    private RecyclerView recyclerView;
    private PoliklinikAdapter poliklinikAdapter;
    private List<String> poliklinikList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliklinik);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        ButtonAntrean = findViewById(R.id.buttonAntrean);

        ButtonAntrean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoliklinikActivity.this, AntreanActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.RvPoli);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        poliklinikList = new ArrayList<>();
        poliklinikList.add(getString(R.string.Poli1));
        poliklinikList.add(getString(R.string.Poli2));
        poliklinikList.add(getString(R.string.Poli3));
        poliklinikList.add(getString(R.string.Poli4));
        poliklinikList.add(getString(R.string.Poli5));

        poliklinikAdapter = new PoliklinikAdapter(poliklinikList, this);
        recyclerView.setAdapter(poliklinikAdapter);
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

    @Override
    public void onItemClick(String poliklinik) {
        buttonName = poliklinik;
        showDatePickerDialog();
    }
}
