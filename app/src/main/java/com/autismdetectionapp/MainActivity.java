package com.autismdetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.util.TinyDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (new TinyDB(MainActivity.this).getBoolean("USER_LOGIN")) {
                    startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                finishAffinity();
            }
        },2000);
    }
}