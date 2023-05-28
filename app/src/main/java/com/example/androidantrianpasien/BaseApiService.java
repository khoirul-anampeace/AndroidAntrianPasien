package com.example.androidantrianpasien;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    @GET("dokter")
    Call<ResponseBody> getDokter();

    @GET("dokter/filter/{kode}")
    Call<ResponseBody> getDokterByPoli(@Path("kode") String kode);

    @GET("poli")
    Call<ResponseBody> getPoli();

    @GET("book/detail/{nik}")
    Call<ResponseBody> getBookByNik(@Path("nik") String nik);

    @FormUrlEncoded
    @POST("book/add")
    Call<ResponseBody> requestDaftar(@Field("kode_poli") String poli,
                                     @Field("kode_dokter") String dokter,
                                     @Field("kode_pembayaran") String pembayaran,
                                     @Field("tanggal_booking") String tanggal,
                                     @Field("status") String status,
                                     @Field("nik") String nik,
                                     @Field("nama_pasien") String pasien,
                                     @Field("tanggal_lahir") String lahir,
                                     @Field("no_kontak") String kontak);
}
