package com.autismdetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.model.ResultModel;
import com.autismdetectionapp.util.Constants;
import com.autismdetectionapp.util.TinyDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    com.autismdetectionapp.databinding.ActivityResultBinding binding;
    int totalScore = 0;
    TinyDB tinyDB;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.autismdetectionapp.databinding.ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tinyDB = new TinyDB(ResultActivity.this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        totalScore = Constants.activityOneCount + Constants.activityTwoCount + Constants.activityThreeCount + Constants.breakTheBubbleActivityCount;
        insertResult();
        binding.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
                finishAffinity();
            }
        });

        binding.tvResultScore.setText("Your Total Score: " + (totalScore) + " out of 48");

        if (totalScore < 30) {
            binding.tvResultScoreMeans.setText(R.string.autism_problem_find);
        } else {
            binding.tvResultScoreMeans.setText(R.string.autism_problem_not_find);
        }

    }

    void insertResult() {
        ResultModel resultModel = new ResultModel(Constants.insertedChildName,
                tinyDB.getString("USER_KEY"), (totalScore) + " out of 48", getCurrentDateTime());
        DatabaseReference newRecordRef = mDatabase.child("results").push();
        newRecordRef.setValue(resultModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(ResultActivity.this, "Failed to insert record.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
        finishAffinity();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}