package com.example.androidantrianpasien.Fragment;

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
import android.widget.Spinner;

import com.example.androidantrianpasien.PoliklinikActivity;
import com.example.androidantrianpasien.R;
import com.example.androidantrianpasien.DokterActivity;

public class FragmentBaru extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerPelayanan;
    private Button buttonBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);

        spinnerPelayanan = view.findViewById(R.id.SpinnerPelayanan);
        buttonBack = view.findViewById(R.id.buttonback);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(requireContext(), R.array.pelayanan_array, android.R.layout.simple_spinner_item);
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

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
