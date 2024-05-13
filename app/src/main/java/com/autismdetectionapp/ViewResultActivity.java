package com.autismdetectionapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.adapter.ResultDetailAdapter;
import com.autismdetectionapp.databinding.ActivityViewResultBinding;
import com.autismdetectionapp.model.ResultModel;
import com.autismdetectionapp.util.TinyDB;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewResultActivity extends AppCompatActivity {

    ActivityViewResultBinding binding;
    List<ResultModel> resultModelList = new ArrayList<>();
    ProgressDialog progressDialog;
    TinyDB tinyDB;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(ViewResultActivity.this);
        progressDialog.setTitle("please wait...");
        tinyDB = new TinyDB(ViewResultActivity.this);
        binding.resultDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
                finishAffinity();
            }
        });
        getResultRecord();


    }

    private void getResultRecord() {
        resultModelList.clear();
        progressDialog.show();

        mDatabase.child("results").orderByChild("parentKey").equalTo(tinyDB.getString("USER_KEY"))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        progressDialog.dismiss();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String childName = childSnapshot.child("childName").getValue(String.class);
                            String score = childSnapshot.child("score").getValue(String.class);
                            String dateTime = childSnapshot.child("dateTime").getValue(String.class);
                            resultModelList.add(new ResultModel(childName, score, dateTime));
                        }

                        ResultDetailAdapter adapter = new ResultDetailAdapter(resultModelList, ViewResultActivity.this);
                        binding.viewResultLV.setAdapter(adapter);
                        if (resultModelList.size() > 0) {
                            binding.tvNoResult.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewResultActivity.this, "" + databaseError.toException(), Toast.LENGTH_SHORT).show();
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