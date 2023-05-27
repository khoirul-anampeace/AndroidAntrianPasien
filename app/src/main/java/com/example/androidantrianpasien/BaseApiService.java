package com.example.androidantrianpasien;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BaseApiService {

    @GET("dokter")
    Call<ResponseBody> getDokter();

    @GET("dokter/filter/{kode}")
    Call<ResponseBody> getDokterByPoli(@Path("kode") String kode);

    @GET("poli")
    Call<ResponseBody> getPoli();
}
