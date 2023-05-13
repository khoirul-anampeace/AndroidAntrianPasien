package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIMEOUT = 3000; // Waktu dalam milidetik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, PoliklinikActivity.class));
            finish();
        }, SPLASH_SCREEN_TIMEOUT);
    }
}