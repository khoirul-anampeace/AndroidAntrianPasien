package com.example.androidantrianpasien.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androidantrianpasien.PoliklinikActivity;
import com.example.androidantrianpasien.R;
import com.example.androidantrianpasien.DokterActivity;

import java.util.Calendar;

public class FragmentBaru extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerPelayanan;
    private Button buttonBack;
    private TextView kolomTanggalLahir;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);

        spinnerPelayanan = view.findViewById(R.id.SpinnerPelayanan);
        buttonBack = view.findViewById(R.id.buttonback);
        kolomTanggalLahir = view.findViewById(R.id.KolomTanggalLahir);
        calendar = Calendar.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_item, getResources().getStringArray(R.array.pelayanan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPelayanan.setAdapter(adapter);
        spinnerPelayanan.setOnItemSelectedListener(this);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PoliklinikActivity.class);
                startActivity(intent);
            }
        });

        kolomTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update text pada KolomTanggalLahir dengan tanggal yang dipilih
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        kolomTanggalLahir.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

                // Tampilkan DatePickerDialog
                datePickerDialog.show();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
