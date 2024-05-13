package com.autismdetectionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityFormBinding;
import com.autismdetectionapp.model.Child;
import com.autismdetectionapp.util.Constants;
import com.autismdetectionapp.util.TinyDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding binding;
    ProgressDialog progressDialog;
    TinyDB tinyDB;
    private DatabaseReference mDatabase;
    String gender = "Male", disability = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(FormActivity.this);
        progressDialog.setTitle("please wait...");
        tinyDB = new TinyDB(FormActivity.this);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        binding.rdMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    gender = "Male";
                }
            }
        });

        binding.rdFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    gender = "Female";
                }
            }
        });

        binding.rdNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    disability = "No";
                }
            }
        });

        binding.rdYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    disability = "Yes";
                }
            }
        });

        binding.foodItemDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
                finishAffinity();
            }
        });

        binding.btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    insertChildData();
                }
            }
        });
    }

    private boolean validation() {
        if (binding.edtChildAge.getText().toString().isEmpty()) {
            binding.edtChildAge.setError("fill this field");
            return false;
        } else if (binding.edtChildName.getText().toString().isEmpty()) {
            binding.edtChildName.setError("fill this field");
            return false;
        } else if (binding.edtChildName.getText().toString().length() < 3) {
            binding.edtChildName.setError("Invalid Name");
            return false;
        }
        return true;
    }

    private void insertChildData() {
        progressDialog.show();
        Child child = new Child(binding.edtChildName.getText().toString(),
                gender, disability, tinyDB.getString("USER_KEY"),
                Integer.parseInt(binding.edtChildAge.getText().toString()));
        DatabaseReference newRecordRef = mDatabase.child("child").push();
        newRecordRef.setValue(child)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Constants.insertedChildName = binding.edtChildName.getText().toString();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    Toast.makeText(FormActivity.this, "Child Record Inserted Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                                    finishAffinity();
                                }
                            }, 750);
                        } else {
                            Toast.makeText(FormActivity.this, "Failed to insert record.", Toast.LENGTH_SHORT).show();
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
}