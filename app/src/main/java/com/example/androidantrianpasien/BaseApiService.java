package com.example.androidantrianpasien;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BaseApiService {

    @GET("dokter")
    Call<ResponseBody> getDokter();
}
