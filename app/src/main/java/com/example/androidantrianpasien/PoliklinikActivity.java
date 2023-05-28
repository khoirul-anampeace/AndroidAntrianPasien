package com.example.androidantrianpasien;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidantrianpasien.Adapter.PoliklinikAdapter;
import com.example.androidantrianpasien.Model.ItemPoliklinik;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoliklinikActivity extends AppCompatActivity {
    private Calendar calendar;
    private Button ButtonAntrean;
    private String buttonName;
    private SimpleDateFormat dateFormat;
    private RecyclerView recyclerView;

    Context context;
    BaseApiService apiService;
    PoliklinikAdapter adapter;
    ArrayList<ItemPoliklinik> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poliklinik);
        SharedPreferences mSettings = getBaseContext().getSharedPreferences("Apasien", Context.MODE_PRIVATE);
        String cookieName = mSettings.getString("nik", "missing");
        System.out.println("Nilai Nik " + cookieName);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        ButtonAntrean = findViewById(R.id.buttonAntrean);

        ButtonAntrean.setOnClickListener(v -> {
            Intent intent = new Intent(PoliklinikActivity.this, AntreanActivity.class);
            startActivity(intent);
        });

        adapter = new PoliklinikAdapter(list, this);
        recyclerView = findViewById(R.id.RvPoli);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // koneksi api
        apiService = URLapi.getAPIService();
        getData();
    }

    private void getData() {
        apiService.getPoli().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = object.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        ItemPoliklinik item = new ItemPoliklinik();
                        item.setId(data.getString("id"));
                        item.setKode_poli(data.getString("kode_poli"));
                        item.setNama_poli(data.getString("nama_poli"));
                        list.add(item);
                    }

                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
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
        };
        new DatePickerDialog(PoliklinikActivity.this, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

}
