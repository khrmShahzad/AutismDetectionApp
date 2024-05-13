package com.autismdetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityParentHomePageBinding;
import com.autismdetectionapp.util.Constants;
import com.autismdetectionapp.util.TinyDB;

public class ParentHomePageActivity extends AppCompatActivity {

    ActivityParentHomePageBinding binding;
    TinyDB tinyDB;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParentHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tinyDB = new TinyDB(ParentHomePageActivity.this);
        Constants.insertedChildName = "";
        Constants.activityOneCount = 0;
        Constants.isFirstActivityComplete = false;
        Constants.activityTwoCount = 0;
        Constants.isSecondActivityComplete = false;
        Constants.activityThreeCount = 0;
        Constants.isThirdActivityComplete = false;
        Constants.breakTheBubbleActivityCount = 0;
        Constants.isBreakTheBubbleActivityComplete = false;

        binding.tvUserName.setText("Hello, " + tinyDB.getString("PARENT_NAME"));

        binding.logoutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinyDB.clear();
                Toast.makeText(ParentHomePageActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finishAffinity();
            }

        });

        binding.attemptTestCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FormActivity.class));
                finishAffinity();
            }
        });

        binding.viewHistoryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewResultActivity.class));
                finishAffinity();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}