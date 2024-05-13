package com.autismdetectionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.autismdetectionapp.databinding.ActivityThirdTestBinding;
import com.autismdetectionapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ThirdTestActivity extends AppCompatActivity {

    ActivityThirdTestBinding binding;
    List<Integer> imagesList = new ArrayList<>();
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fillDataInList();
        binding.testthreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });
        initViews();

    }

    private void initViews() {
        displayQuestion(currentIndex);
        binding.btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtCountObject.getText().toString().isEmpty()) {
                    binding.edtCountObject.setError("fill this field");
                } else {
                    answerCounting(currentIndex, Integer.parseInt(binding.edtCountObject.getText().toString()));
                    currentIndex++;
                    if (currentIndex >= imagesList.size()) {
                        Constants.isThirdActivityComplete = true;
                        startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
                        finishAffinity();
                    } else {
                        displayQuestion(currentIndex);
                    }

                }
            }
        });
    }

    private void displayQuestion(int c_Index) {
        binding.tvQuestionNumber.setText("Question " + (c_Index + 1) + " of " + imagesList.size());
        binding.edtCountObject.setText("");
        binding.edtCountObject.setHint("Count the Object Write Answer");
        binding.objectImage.setImageResource(imagesList.get(c_Index));
    }

    private void answerCounting(int cIndex, int cAnswer) {
        if (cIndex == 0) {
            if (cAnswer == 3) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 1) {
            if (cAnswer == 16) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 2) {
            if (cAnswer == 15) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 3) {
            if (cAnswer == 24) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 4) {
            if (cAnswer == 5) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 5) {
            if (cAnswer == 16) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 6) {
            if (cAnswer == 4) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 7) {
            if (cAnswer == 5) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 8) {
            if (cAnswer == 3) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        } else if (cIndex == 9) {
            if (cAnswer == 5) {
                Constants.activityThreeCount = Constants.activityThreeCount + 1;
            }
        }
    }

    private void fillDataInList() {
        imagesList.clear();
        imagesList.add(R.drawable.counter_one);
        imagesList.add(R.drawable.counter_two);
        imagesList.add(R.drawable.counter_three);
        imagesList.add(R.drawable.counter_four);
        imagesList.add(R.drawable.counter_five);
        imagesList.add(R.drawable.counter_six);
        imagesList.add(R.drawable.counter_seven);
        imagesList.add(R.drawable.counter_eight);
        imagesList.add(R.drawable.counter_nine);
        imagesList.add(R.drawable.counter_ten);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit Activity");
        builder.setMessage("Are you sure you want to proceed? You'll lose your Data");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Constants.activityThreeCount = 0;
                startActivity(new Intent(getApplicationContext(), TestCategoryActivity.class));
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