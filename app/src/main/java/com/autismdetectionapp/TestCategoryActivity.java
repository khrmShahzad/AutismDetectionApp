package com.autismdetectionapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityTestCategoryBinding;
import com.autismdetectionapp.util.Constants;

public class TestCategoryActivity extends AppCompatActivity {

    ActivityTestCategoryBinding binding;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressDialog = new ProgressDialog(TestCategoryActivity.this);
        progressDialog.setTitle("Generating Your Result");
        progressDialog.setMessage("please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        binding.testCategoriesBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });

        if (Constants.isFirstActivityComplete) {
            binding.firstActivityCheckIV.setImageResource(R.drawable.checked_icon);
        } else {
            binding.firstActivityCheckIV.setImageResource(R.drawable.unchecked_icon);
        }

        if (Constants.isSecondActivityComplete) {
            binding.secondActivityCheckIV.setImageResource(R.drawable.checked_icon);
        } else {
            binding.secondActivityCheckIV.setImageResource(R.drawable.unchecked_icon);
        }

        if (Constants.isThirdActivityComplete) {
            binding.thirdActivityCheckIV.setImageResource(R.drawable.checked_icon);
        } else {
            binding.thirdActivityCheckIV.setImageResource(R.drawable.unchecked_icon);
        }

        if (Constants.isBreakTheBubbleActivityComplete) {
            binding.breaktheBubbleActivityCheckIV.setImageResource(R.drawable.checked_icon);
        } else {
            binding.breaktheBubbleActivityCheckIV.setImageResource(R.drawable.unchecked_icon);
        }

        binding.breakTheBubbleActivityCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constants.isBreakTheBubbleActivityComplete) {
                    Constants.breakTheBubbleActivityCount = 0;
                    startActivity(new Intent(getApplicationContext(), BreakTheBubbleActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(TestCategoryActivity.this, "You already complete this activity", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.btnGenerateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.isFirstActivityComplete
                        && Constants.isSecondActivityComplete
                        && Constants.isThirdActivityComplete) {
                    ShowResultDialog();
                } else {
                    Toast.makeText(TestCategoryActivity.this, "Complete Above Activities", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.firstActivityCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constants.isFirstActivityComplete) {
                    Constants.activityOneCount = 0;
                    startActivity(new Intent(getApplicationContext(), ActivityOneTestActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(TestCategoryActivity.this, "You already complete this activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.secondActivityCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constants.isSecondActivityComplete) {
                    Constants.activityTwoCount = 0;
                    startActivity(new Intent(getApplicationContext(), SecondTestActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(TestCategoryActivity.this, "You already complete this activity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.thirdActivityCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Constants.isThirdActivityComplete) {
                    Constants.activityThreeCount = 0;
                    startActivity(new Intent(getApplicationContext(), ThirdTestActivity.class));
                    finishAffinity();
                } else {
                    Toast.makeText(TestCategoryActivity.this, "You already complete this activity", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void ShowResultDialog() {
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                startActivity(new Intent(getApplicationContext(), ResultActivity.class));
                overridePendingTransition(0, 0);
                finishAffinity();
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Test");
        builder.setMessage("Are you sure you want to proceed?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Constants.insertedChildName = "";
                startActivity(new Intent(getApplicationContext(), ParentHomePageActivity.class));
                finishAffinity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}