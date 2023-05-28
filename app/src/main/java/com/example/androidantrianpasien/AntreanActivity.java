package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidantrianpasien.Adapter.DokterAdapter;
import com.example.androidantrianpasien.Model.ItemDokter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AntreanActivity extends AppCompatActivity {

    private Button buttonkembali;

    String nik;
    BaseApiService apiService;

    DokterAdapter adapter;
    ArrayList<ItemDokter> list = new ArrayList<>();

    private TextView TanggalView, NomorAntrianView, PoliView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrean);

        TanggalView = findViewById(R.id.tanggalview);
        NomorAntrianView = findViewById(R.id.nomorantrianview);
        PoliView = findViewById(R.id.poliview);

        buttonkembali = findViewById(R.id.buttonback);
        buttonkembali.setOnClickListener(v -> finish());

        // Baca sharedpreference
        SharedPreferences mSettings = getBaseContext().getSharedPreferences("Apasien", Context.MODE_PRIVATE);
        nik = mSettings.getString("nik", "missing");
        System.out.println("Nilai Nik " + nik);

        // koneksi api
        apiService = URLapi.getAPIService();
        getData();
    }

    private void getData() {
        apiService.getBookByNik(nik).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject result = new JSONObject(response.body().string());

                    String no = result.getJSONObject("data").getString("no_antrian");
                    String poli = result.getJSONObject("data").getString("nama_poli");
                    String tanggal = result.getJSONObject("data").getString("tanggal_booking");

                    TanggalView.setText("Tanggal Booking = " + tanggal);
                    PoliView.setText("Poli = " + poli);
                    NomorAntrianView.setText(no);
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}