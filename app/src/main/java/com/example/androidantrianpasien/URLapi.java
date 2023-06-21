package com.example.androidantrianpasien;

public class URLapi {

   public static final String BASE_URL_API = "http://192.168.112.37:8080/antrianpasien/public/api/";
//   public static final String BASE_URL_API = "http://192.168.43.55:8080/antrianpasien/public/api/";

//   public static final String BASE_URL_API = "http://50.50.50.160:8080/antrianpasien/public/api/";

   public static BaseApiService getAPIService(){
      return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
   }
}
