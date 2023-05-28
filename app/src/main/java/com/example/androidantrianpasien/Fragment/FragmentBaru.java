package com.example.androidantrianpasien.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidantrianpasien.AntreanActivity;
import com.example.androidantrianpasien.BaseApiService;
import com.example.androidantrianpasien.DaftarActivity;
import com.example.androidantrianpasien.PoliklinikActivity;
import com.example.androidantrianpasien.R;
import com.example.androidantrianpasien.DokterActivity;
import com.example.androidantrianpasien.URLapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBaru extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText kolomNIK, kolomNamaPasien, kolomKontak;
    private Spinner spinnerPelayanan;
    private Button buttonBack, buttonDaftar;
    private TextView kolomTanggalLahir;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    BaseApiService apiService;

    String lahir = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_baru, container, false);

        apiService = URLapi.getAPIService();

        spinnerPelayanan = view.findViewById(R.id.SpinnerPelayanan);
        kolomTanggalLahir = view.findViewById(R.id.KolomTanggalLahir);
        kolomNIK = view.findViewById(R.id.KolomNIK);
        kolomNamaPasien = view.findViewById(R.id.KolomNamaPasien);
        kolomKontak = view.findViewById(R.id.KolomKontak);
        buttonBack = view.findViewById(R.id.buttonback);
        buttonDaftar = view.findViewById(R.id.btnDaftar);

        calendar = Calendar.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.spinner_item, getResources().getStringArray(R.array.pelayanan_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPelayanan.setAdapter(adapter);
        spinnerPelayanan.setOnItemSelectedListener(this);

        buttonBack.setOnClickListener(v -> {
            getActivity().finish();
        });

        buttonDaftar.setOnClickListener(view1 -> {
            try {
                proceedDaftar();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        kolomTanggalLahir.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(requireContext(), (view1, year1, month1, dayOfMonth1) -> {
                // Update text pada KolomTanggalLahir dengan tanggal yang dipilih
                String selectedDate = dayOfMonth1 + "/" + (month1 + 1) + "/" + year1;
                kolomTanggalLahir.setText(selectedDate);
                lahir = year1 + "-" + ((month1 + 1) < 9 ? "0" : "") + (month1 + 1) + "-" + (dayOfMonth1 < 9 ? "0" : "") + dayOfMonth1;
            }, year, month, dayOfMonth);

            // Tampilkan DatePickerDialog
            datePickerDialog.show();
        });

        return view;
    }

    private void proceedDaftar() throws ParseException {
        String tgl = ((DaftarActivity) getActivity()).tanggal;

        DateFormat df_new = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df_old = new SimpleDateFormat("dd-MM-yyyy");

        String poli = ((DaftarActivity) getActivity()).poli;
        String dokter = ((DaftarActivity) getActivity()).dokter;
        String tanggal = df_new.format(df_old.parse(tgl));
        String pembayaranlama = spinnerPelayanan.getSelectedItem().toString().trim();
        String pembayaran = "";
        if (pembayaranlama.equals("BPJS")){
            pembayaran = "JP001";
        } else if (pembayaranlama.equals("KIS")){
            pembayaran = "JP002";
        } else if (pembayaranlama.equals("UMUM")){
            pembayaran = "JP003";
        }else {
            pembayaran = "Gagal Bayar";
        }
        String status = "Menuggu konfirmasi";

        String nik = kolomNIK.getText().toString().trim();
        String pasien = kolomNamaPasien.getText().toString().trim();
        String lahir_ = lahir;
        String kontak = kolomKontak.getText().toString().trim();

        apiService.requestDaftar(poli, dokter, pembayaran, tanggal, status, nik, pasien, lahir_, kontak).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Berhasil mendaftar!", Toast.LENGTH_SHORT).show();

                    try {
                        JSONObject result = new JSONObject(response.body().string());

                        String id = result.getJSONObject("data").getString("id");
                        String kode = result.getJSONObject("data").getString("kode_registrasi");
                        String antrian = result.getJSONObject("data").getString("no_antrian");
                        String nik = result.getJSONObject("data").getString("nik");
                        String tanggal = result.getJSONObject("data").getString("tanggal_booking");
                        String status = result.getJSONObject("data").getString("status");

                        SharedPreferences mSettings = getActivity().getSharedPreferences("Apasien", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString("nik", nik);
                        editor.commit();

                        getActivity().finish();
                        startActivity(new Intent(getContext(), AntreanActivity.class));
                    } catch (JSONException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
