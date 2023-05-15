package com.example.androidantrianpasien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView mSplashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mSplashImage = findViewById(R.id.imageView);
        mSplashImage = findViewById(R.id.imageView2);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mSplashImage.startAnimation(fadeIn);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isFirstTime();
            }
        },2000);
    }

    private void isFirstTime() {
        SharedPreferences preferences = getApplication().getSharedPreferences("poliklinik", Context.MODE_PRIVATE);
        boolean isFirstTIme = preferences.getBoolean("isFirstTime", true);
        if(isFirstTIme){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstTime", false);
            editor.apply();

            startActivity(new Intent(SplashActivity.this, PoliklinikActivity.class));
            finish();
        }
        else {
            startActivity(new Intent(SplashActivity.this, PoliklinikActivity.class));
            finish();
        }



    }
}