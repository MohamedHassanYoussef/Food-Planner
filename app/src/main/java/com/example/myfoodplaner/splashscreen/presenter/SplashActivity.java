package com.example.myfoodplaner.splashscreen.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.SignUpScreen1;
import com.example.myfoodplaner.login.LoginScreen1;

public class SplashActivity extends AppCompatActivity {


    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash2);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSharedPreferences = getSharedPreferences("mySp", MODE_PRIVATE);
                boolean isFirstTime = mSharedPreferences.getBoolean("firstTimeToOpen", true);

                boolean isAuthenticated = false;
                if (isFirstTime) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();
                    Intent intent = new Intent(SplashActivity.this, LoginScreen1.class);
                    //Intent intent = new Intent(Splash.this,Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }  else {
                    Intent intent = new Intent(SplashActivity.this, SignUpScreen1.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

        }, 4000);


    }
}