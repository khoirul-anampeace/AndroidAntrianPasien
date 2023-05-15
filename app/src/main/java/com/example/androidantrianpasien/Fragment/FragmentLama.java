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
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.androidantrianpasien.PoliklinikActivity;
import com.example.androidantrianpasien.R;
import com.example.androidantrianpasien.DokterActivity;

import java.util.Calendar;

public class FragmentLama extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView kolomTanggalLahir;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    public FragmentLama() {
        // Required empty public constructor
    }

    public static FragmentLama newInstance(String param1, String param2) {
        FragmentLama fragment = new FragmentLama();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lama, container, false);
        View buttonBack = view.findViewById(R.id.buttonback);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PoliklinikActivity.class);
                startActivity(intent);
            }
        });
        kolomTanggalLahir = view.findViewById(R.id.KolomTanggalLahir);
        calendar = Calendar.getInstance();

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
}
