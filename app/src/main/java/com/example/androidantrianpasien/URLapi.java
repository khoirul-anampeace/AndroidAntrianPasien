package com.example.androidantrianpasien;

public class URLapi {

   public static final String BASE_URL_API = "http://30.30.30.22:8080/antrianpasien/public/api/";

   public static BaseApiService getAPIService(){
      return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
   }

}