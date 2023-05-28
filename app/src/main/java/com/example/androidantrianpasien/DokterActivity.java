package com.example.androidantrianpasien;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.Adapter.DokterAdapter;
import com.example.androidantrianpasien.Model.ItemDokter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DokterActivity extends AppCompatActivity {

    private TextView TanggalView;
    private RecyclerView recyclerView;
    private Button btnNext;

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datepicker;
    SimpleDateFormat simpleDateFormat;

    Context context;
    BaseApiService apiService;
    DokterAdapter adapter;
    ArrayList<ItemDokter> list = new ArrayList<>();

    String kode, nama, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        TanggalView = findViewById(R.id.TanggalView);
        recyclerView = findViewById(R.id.RvDokter);
        btnNext = findViewById(R.id.BtnLanjutkan);

        context = this;
        adapter = new DokterAdapter(list, this);
        recyclerView = findViewById(R.id.RvDokter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // dapatkan dari activity sebelumnya
        Intent b = getIntent();
        kode = b.getStringExtra("kode");
        nama = b.getStringExtra("nama");

        Date c = Calendar.getInstance().getTime();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        // di set pertama kali
        String displayText = "NamaPoli: " + nama + "\nTanggal CheckUp: " + simpleDateFormat.format(c);
        tanggal = simpleDateFormat.format(c);
        TanggalView.setText(displayText);

        // ketika tanggal di klik
        TanggalView.setOnClickListener(view -> {
            DatePickerDialog dialog = new DatePickerDialog(context, datepicker,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dialog.getDatePicker().setMinDate(System.currentTimeMillis());
            dialog.show();
        });

        // ketika tanggal di ubah
        datepicker = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            String text = "NamaPoli: " + nama + "\nTanggal CheckUp: " + simpleDateFormat.format(calendar.getTime());
            TanggalView.setText(text);
            tanggal = simpleDateFormat.format(calendar.getTime());
        };

        // ketika di klik next
        btnNext.setOnClickListener(view -> {
            if (adapter.getSelected() != null) {
                Intent intent = new Intent(context, DaftarActivity.class);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("poli", kode);
                intent.putExtra("poli_nama", nama);
                intent.putExtra("dokter", adapter.getSelected().getKode_dokter());
                intent.putExtra("dokter_nama", adapter.getSelected().getNama_dokter());
                startActivity(intent);
            } else {
                Toast.makeText(context, "Tentukan dokter terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        // koneksi api
        apiService = URLapi.getAPIService();
        getData();
    }

    private void getData() {
        apiService.getDokterByPoli(kode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = object.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        ItemDokter item = new ItemDokter();
                        item.setId(data.getString("id"));
                        item.setKode_dokter(data.getString("kode_dokter"));
                        item.setKode_poli(data.getString("kode_poli"));
                        item.setNama_dokter(data.getString("nama_dokter"));
                        list.add(item);
                    }

                    adapter.notifyDataSetChanged();
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